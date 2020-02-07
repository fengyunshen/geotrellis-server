/*
 * Copyright 2020 Azavea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package geotrellis.server.ogc

import geotrellis.server.ogc.wms.wmsScope
import geotrellis.proj4.CRS
import geotrellis.vector.Extent
import geotrellis.raster.TileLayout
import geotrellis.raster.render.{ColorMap, ColorRamp}

import com.azavea.maml.ast._
import com.azavea.maml.ast.codec.tree._
import com.typesafe.config.{ConfigRenderOptions, ConfigValue}
import io.circe._
import io.circe.parser._
import pureconfig._
import pureconfig.error.CannotConvert
import pureconfig.generic.FieldCoproductHint
import pureconfig.generic.auto._

import scala.util.Try

/** A grab bag of [[ConfigReader]] instances necessary to read the configuration */
package object conf {
  /** Starting 0.11.0 https://github.com/pureconfig/pureconfig/blob/bfc74ce436297b2a9da091e04d362be61108a3cf/CHANGELOG.md#0110-may-9-2019
   * The default transformation in FieldCoproductHint changed
   * from converting class names to lower case to converting them to kebab case.
   */
  implicit def coproductHint[T] = new FieldCoproductHint[T]("type") {
    override def fieldValue(name: String): String = name.toLowerCase
  }

  implicit def circeJsonReader: ConfigReader[Json] =
    ConfigReader[ConfigValue].emap { cv =>
      val renderOptions = ConfigRenderOptions.concise().setJson(true)
      val jsonString = cv.render(renderOptions)
      parse(jsonString) match {
        case Left(parsingFailure) => Left(CannotConvert(jsonString, "json",  parsingFailure.getMessage))
        case Right(json) => Right(json)
      }
    }

  implicit val expressionReader: ConfigReader[Expression] =
    ConfigReader[Json].map { expressionJson =>
      expressionJson.as[Expression] match {
        case Right(success) => success
        case Left(err) => throw err
      }
    }

  implicit def colorRampReader: ConfigReader[ColorRamp] =
    ConfigReader[List[String]].map { colors =>
      ColorRamp(colors.map(java.lang.Long.decode(_).toInt))
    }

  implicit def colormapReader: ConfigReader[ColorMap] =
    ConfigReader[Map[String, String]].map { cmap =>
      ColorMap(cmap.map { case (k, v) => (k.toDouble -> java.lang.Long.decode(v).toInt) }.toMap)
    }

  implicit def keywordConfigReader: ConfigReader[opengis.wms.Keyword] =
    ConfigReader[String].map { str =>
      opengis.wms.Keyword(str)
    }

  implicit def nameConfigReader: ConfigReader[opengis.wms.Name] =
    ConfigReader[String].map { str =>
      opengis.wms.Name.fromString(str, wmsScope)
    }

  implicit val crsReader: ConfigReader[CRS] =
    ConfigReader[Int].map { epsgCode =>
      Try(CRS.fromEpsgCode(epsgCode)).toOption match {
        case Some(crs) => crs
        case None => throw new Exception(s"Invalid EPSG code: ${epsgCode}")
      }
    }

  implicit val extentReader: ConfigReader[Extent] =
    ConfigReader[(Double, Double, Double, Double)].map { case extent@(xmin, ymin, xmax, ymax) =>
      Try(Extent(xmin, ymin, xmax, ymax)).toOption match {
        case Some(extent) => extent
        case None => throw new Exception(s"Invalid extent: ${extent}. Should be (xmin, ymin, xmax, ymax)")
      }
    }

  implicit val tileLayoutReader: ConfigReader[TileLayout] =
    ConfigReader[(Int, Int, Int, Int)].map { case layout@(layoutCols, layoutRows, tileCols, tileRows) =>
      Try(TileLayout(layoutCols, layoutRows, tileCols, tileRows)).toOption match {
        case Some(layout) => layout
        case None => throw new Exception(s"Invalid layout: ${layout}. Should be (layoutCols, layoutRows, tileCols, tileRows)")
      }
    }

  /** An alternative AST reading strategy that uses a separate json file */
  //private lazy val s3client = AmazonS3ClientBuilder.defaultClient()

  //def readString(uri: URI): String = uri.getScheme match {
  //  case "http" | "https" =>
  //    //Http(uri.toString).method("GET").asString.body
  //    throw new Exception("http-backed maml is not supported at this time, please use s3 or filesystem storage")
  //  case "file" =>
  //    Source.fromFile(uri.getPath).getLines.mkString
  //  case "s3" =>
  //    val s3uri = new AmazonS3URI(uri)
  //    val objectIS = s3client
  //      .getObject(s3uri.getBucket, s3uri.getKey)
  //      .getObjectContent()
  //    // found this method for IS => String from: https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
  //    new BufferedReader(new InputStreamReader(objectIS))
  //      .lines()
  //      .collect(Collectors.joining("\n"));
  //  case _ =>
  //    throw new Exception(
  //      "A valid URI is required...")
  //}

  //  implicit val expressionReader: ConfigReader[Expression] =
  //  ConfigReader[URI].map { expressionURI =>
  //    val expressionString = readString(expressionURI)
  //    decode[Expression](expressionString) match {
  //      case Right(expr) => expr
  //      case Left(err) => throw err
  //    }
  //  }
}
