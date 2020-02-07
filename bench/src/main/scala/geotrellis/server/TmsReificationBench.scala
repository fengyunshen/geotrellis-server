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

// package geotrellis.server

// import geotrellis.server.vlm.geotiff._
// import geotrellis.server.vlm.gdal._

// import geotrellis.raster.MultibandTile
// import com.azavea.maml.ast._
// import com.azavea.maml.error._
// import com.azavea.maml.util.Square
// import com.azavea.maml.eval.BufferingInterpreter
// import cats.effect._
// import org.openjdk.jmh.annotations._
// import org.gdal.gdal.gdalJNI

// import scala.concurrent.ExecutionContext
// import java.net.URI


// @BenchmarkMode(Array(Mode.AverageTime))
// @State(Scope.Thread)
// class TmsReificationBench {

//   // gdal performance will be obscured by the caching it attempts
//   gdalJNI.SetConfigOption("GDAL_CACHEMAX", "0")

//   implicit var contextShift = IO.contextShift(ExecutionContext.global)

//   // NDVI
//   val ast =
//     Division(List(
//       Subtraction(List(
//         RasterVar("red"),
//         RasterVar("nir"))),
//       Addition(List(
//         RasterVar("red"),
//         RasterVar("nir"))
//       ))
//     )

//   // red, green, NIR bands which should have data for z/x/y 9/454/200
//   val geotiffVars = Map(
//     "red" -> GeoTiffNode(new URI("https://s3.amazonaws.com/geotrellis-test/daunnc/r-g-nir-with-ovrs.tif"), 0, None),
//     "nir" -> GeoTiffNode(new URI("https://s3.amazonaws.com/geotrellis-test/daunnc/r-g-nir-with-ovrs.tif"), 2, None)
//   )
//   val gdalVars = Map(
//     "red" -> GDALNode(new URI("https://s3.amazonaws.com/geotrellis-test/daunnc/r-g-nir-with-ovrs.tif"), 0, None),
//     "nir" -> GDALNode(new URI("https://s3.amazonaws.com/geotrellis-test/daunnc/r-g-nir-with-ovrs.tif"), 2, None)
//   )

//   @Setup(Level.Trial)
//   def setup(): Unit = {}

//   @Benchmark
//   def geotiffLayerTms: Interpreted[MultibandTile] = {
//     val eval = LayerTms(IO(ast), IO(geotiffVars), BufferingInterpreter.DEFAULT)
//     eval(9, 454, 200).unsafeRunSync
//   }

//   @Benchmark
//   def gdalLayerTms: Interpreted[MultibandTile] = {
//     val eval = LayerTms(IO(ast), IO(gdalVars), BufferingInterpreter.DEFAULT)
//     eval(9, 454, 200).unsafeRunSync
//   }
// }
