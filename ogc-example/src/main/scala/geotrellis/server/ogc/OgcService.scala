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

import geotrellis.server.ogc.wcs._
import geotrellis.server.ogc.wms._
import geotrellis.server.ogc.wmts._

import org.http4s._
import org.http4s.dsl.Http4sDsl
import cats.Parallel
import cats.effect._
import org.typelevel.log4cats.Logger
import org.log4s.getLogger

import java.net.URL

class OgcService[F[_]: Async: Parallel: Logger](
  wmsModel: Option[WmsModel[F]],
  wcsModel: Option[WcsModel[F]],
  wmtsModel: Option[WmtsModel[F]],
  serviceUrl: URL
) extends Http4sDsl[F] {
  val logger = getLogger

  val wcsView = wcsModel.map(new WcsView(_, serviceUrl))
  val wmsView = wmsModel.map(new WmsView(_, serviceUrl))
  val wmtsView = wmtsModel.map(new WmtsView(_, serviceUrl))

  // Predicates for choosing a service
  def isWcsReq(key: String, value: String) = key.toLowerCase == "service" && value.toLowerCase == "wcs"
  def isWmsReq(key: String, value: String) = key.toLowerCase == "service" && value.toLowerCase == "wms"
  def isWmtsReq(key: String, value: String) = key.toLowerCase == "service" && value.toLowerCase == "wmts"

  def routes: HttpRoutes[F] =
    HttpRoutes.of[F] {
      case req @ GET -> Root if req.params.exists((isWcsReq _).tupled) =>
        logger.trace(s"WCS: $req")
        wcsView
          .map(_.responseFor(req))
          .getOrElse(NotFound())
      case req @ GET -> Root if req.params.exists((isWmsReq _).tupled) =>
        logger.trace(s"WMS: $req")
        wmsView
          .map(_.responseFor(req))
          .getOrElse(NotFound())
      case req @ GET -> Root if req.params.exists((isWmtsReq _).tupled) =>
        logger.trace(s"WMTS: $req")
        wmtsView
          .map(_.responseFor(req))
          .getOrElse(NotFound())
      case req =>
        logger.warn(s"""Recv'd UNHANDLED request: $req""")
        NotFound()
    }
}
