/*
 * Copyright 2021 Azavea
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

package geotrellis.stac.raster

import geotrellis.stac._
import geotrellis.proj4.CRS
import geotrellis.raster.{CellSize, CellType, GridExtent, RasterMetadata, SourceName}

case class StacItemAssetMetadata(
  name: SourceName,
  crs: CRS,
  bandCount: Int,
  cellType: CellType,
  gridExtent: GridExtent[Long],
  resolutions: List[CellSize],
  stacItemAsset: StacItemAsset
) extends RasterMetadata {
  def attributes: Map[String, String] = stacItemAsset.item.properties.toMap
  def attributesForBand(band: Int): Map[String, String] = Map.empty
}
