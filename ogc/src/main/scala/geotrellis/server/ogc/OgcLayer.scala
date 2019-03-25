package geotrellis.server.ogc

import geotrellis.server._
import geotrellis.contrib.vlm._
import geotrellis.raster._
import geotrellis.raster.reproject.ReprojectRasterExtent
import geotrellis.vector.Extent
import geotrellis.proj4.CRS
import com.azavea.maml.ast._

import com.typesafe.scalalogging.LazyLogging
import cats.effect._
import cats.data.{NonEmptyList => NEL}

/**
 * Layer instances are sufficent to produce displayed the end product of 'get map'
 *  requests. They are produced in [[RasterSourcesModel]] from a combination of a WMS 'GetMap'
 *  (or whatever the analogous request in whatever OGC service is being produced) and an instance
 *  of [[OgcSource]]
 */
sealed trait OgcLayer {
  def name: String
  def title: String
  def crs: CRS
  def style: Option[OgcStyle]
}

case class SimpleOgcLayer(
  name: String,
  title: String,
  crs: CRS,
  source: RasterSource,
  style: Option[OgcStyle]
) extends OgcLayer

case class MapAlgebraOgcLayer(
  name: String,
  title: String,
  crs: CRS,
  parameters: Map[String, SimpleOgcLayer],
  algebra: Expression,
  style: Option[OgcStyle]
) extends OgcLayer

object SimpleOgcLayer extends LazyLogging {
  implicit val simpleOgcReification = new ExtentReification[SimpleOgcLayer] {
    def extentReification(self: SimpleOgcLayer)(implicit contextShift: ContextShift[IO]): (Extent, CellSize) => IO[ProjectedRaster[MultibandTile]] =
      (extent: Extent, cs: CellSize) =>  IO {
        logger.debug(s"attempting to retrieve layer $self at extent $extent with cell size of $cs")
        val raster: Raster[MultibandTile] = self.source
          .reprojectToGrid(self.crs, RasterExtent(extent, cs))
          .read(extent)
          .getOrElse(throw new Exception(s"Unable to retrieve layer $self at extent $extent with cell size of $cs"))
        logger.debug(s"Successfully retrieved layer $self at extent $extent with cell size of $cs")

        ProjectedRaster(raster, self.crs)
      }
  }

  implicit val simpleOgcHasRasterExtents: HasRasterExtents[SimpleOgcLayer] = new HasRasterExtents[SimpleOgcLayer] {
    def rasterExtents(self: SimpleOgcLayer)(implicit contextShift: ContextShift[IO]): IO[NEL[RasterExtent]] =
      IO {
        val resolutions = self.source.resolutions.map { ge =>
          ReprojectRasterExtent(ge.toRasterExtent, self.source.crs, self.crs)
        }
        NEL.fromList(resolutions)
          .getOrElse(NEL(ReprojectRasterExtent(self.source.rasterExtent, self.source.crs, self.crs), Nil))
      }
  }
}