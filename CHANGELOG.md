# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [4.6.0] - 2024-05-13

## Added
- Add Azure RangeReaders [#386](https://github.com/geotrellis/geotrellis-server/pull/386)
- Add GetFeatureInfoExtended support [#388](https://github.com/geotrellis/geotrellis-server/pull/388)
- Add Rasterio Affine Transform support [#390](https://github.com/geotrellis/geotrellis-server/pull/390)

## Changed
- STAC Assets regex selectors support [#388](https://github.com/geotrellis/geotrellis-server/pull/388)
- GetFeatureInfo and GetFeatureInfoExtended pixel values field name change [#389](https://github.com/geotrellis/geotrellis-server/pull/389)
- WMTSModel add GeoTrellisOgcSource match item [#395](https://github.com/geotrellis/geotrelis-server/pull/395)
- Update GeoTrellis Server deps [#396](https://github.com/geotrellis/geotrelis-server/pull/396)

## [4.5.0] - 2021-07-10

## Added
- Add MosaicRasterSourceIO logging [#366](https://github.com/geotrellis/geotrellis-server/pull/366)
- Use tofu.Mid for the enchanced logging [#367](https://github.com/geotrellis/geotrellis-server/pull/367)
- Add STAC Page Limit option [#374](https://github.com/geotrellis/geotrellis-server/pull/374)
- Expose MAML layers targetCellType [#380](https://github.com/geotrellis/geotrellis-server/pull/380)

## Fixed
- WMS Parent Layer default time should be omitted [#368](https://github.com/geotrellis/geotrellis-server/pull/368)
- Fix WCS DescribeCoverage extents axis ordering [#369](https://github.com/geotrellis/geotrellis-server/pull/369)
- Fix TIFF CellType of identity MAML Layers [#375](https://github.com/geotrellis/geotrellis-server/pull/375)
- Fix InterpolatedColorMap.render function [#381](https://github.com/geotrellis/geotrellis-server/pull/381)

## Changed
- OgcTime logic cleanup [#371](https://github.com/geotrellis/geotrellis-server/pull/371)
- Change RGB nodata handling [#373](https://github.com/geotrellis/geotrellis-server/pull/373)
- Update STAC up to v1.0.0 [#376](https://github.com/geotrellis/geotrellis-server/pull/376)

## [4.4.0] - 2021-04-30

## Fixed
- Service endpoints reject requests with unsupported version parameters [#313](https://github.com/geotrellis/geotrellis-server/pull/313)
- Axis Ordering in a wms getmap [#302](https://github.com/geotrellis/geotrellis-server/issues/302)
- Interoperability of STAC and non-STAC layers in mapalgebra layers [#309](https://github.com/geotrellis/geotrellis-server/issues/309)
- OGC endpoints format parameter and HTTP response mime-type not matching [#255](https://github.com/geotrellis/geotrellis-server/issues/255)
- WMS 1.3.0 Boolean representation [#332](https://github.com/geotrellis/geotrellis-server/issues/332)
- Fix WCS projections support [#361](https://github.com/geotrellis/geotrellis-server/pull/361)
- STAC-example cleanup [#363](https://github.com/geotrellis/geotrellis-server/pull/363)

## Added
- WCS services support configuration of `supported-projections` [#314](https://github.com/geotrellis/geotrellis-server/pull/314)
- Add WCS 1.1.0 as the supported version [#330](https://github.com/geotrellis/geotrellis-server/pull/330)
- STAC Collections support [#338](https://github.com/geotrellis/geotrellis-server/issues/338)
- StacAssetRasterSource improvements [#326](https://github.com/geotrellis/geotrellis-server/issues/326)
- StacCollectionRasterSource implementation [#340](https://github.com/geotrellis/geotrellis-server/issues/340)
- WCS Rendering formats support [#195](https://github.com/geotrellis/geotrellis-server/issues/195)
- GeoTrellis Server STAC Package [#350](https://github.com/geotrellis/geotrellis-server/issues/350)
- STAC Collection temporal extents support [#347](https://github.com/geotrellis/geotrellis-server/issues/347)
- OGC Services configurable time representation [#324](https://github.com/geotrellis/geotrellis-server/issues/324)
- Default time period configurable on the WMS layer with a time dimension [#325](https://github.com/geotrellis/geotrellis-server/issues/325)
- STAC Periodic Temporal extent support [#354](https://github.com/geotrellis/geotrellis-server/issues/354)
- WMS GetFeatureInfo [#321](https://github.com/geotrellis/geotrellis-server/issues/321)
- MosaicRasterSource improvements [#344](https://github.com/geotrellis/geotrellis-server/issues/344)

### Changed
- Update GT Server STAC4S dependency [#319](https://github.com/geotrellis/geotrellis-server/issues/319)
- Unnecessary mandatory parameters on WCS 1.1.1 GetCoverage request [#306](https://github.com/geotrellis/geotrellis-server/issues/306)

## [4.3.0] - 2021-02-12

## Added
- Logging into STAC API Client [#293](https://github.com/geotrellis/geotrellis-server/pull/293)

### Changed
- *BREAKING* Typeclasses no longer bind the effect type to IO [#284](https://github.com/geotrellis/geotrellis-server/pull/284)
- Optimize MapAlgebraStacOgcRepositories [#291](https://github.com/geotrellis/geotrellis-server/pull/291)
- Update STAC4s up to 0.0.11 with STAC 1.0.0-beta.1 support [#295](https://github.com/geotrellis/geotrellis-server/pull/295)

## Fixed
- Fix STAC API TemporalExtent JSON representation [#293](https://github.com/geotrellis/geotrellis-server/pull/293)
- XML responses in OGC services include a default namespace [#311](https://github.com/geotrellis/geotrellis-server/pull/311)

## [4.2.0] - 2020-06-23

### Added
- Configurable ResampleMethod in source definitions [#229](https://github.com/geotrellis/geotrellis-server/issues/229)
- Enable TargetCell parameter for focal operations [#212](https://github.com/geotrellis/geotrellis-server/issues/212)
- WMS Extended capabilities and operations [#235](https://github.com/geotrellis/geotrellis-server/issues/235)
- WCS GetCapabilities user defined parameters [#237](https://github.com/geotrellis/geotrellis-server/issues/237)
- WMS GetMap support of extended parameters [#236](https://github.com/geotrellis/geotrellis-server/issues/236)
- WCS GetCoverage support of extended parameters [#238](https://github.com/geotrellis/geotrellis-server/issues/238)
- ColorRampStyle.clampWithColor option to render colors outside the requested render range as colors in the ramp instead of as transparent pixels [#220](https://github.com/geotrellis/geotrellis-server/issues/220)
- Expose overview strategy into the layers configuration [#252](https://github.com/geotrellis/geotrellis-server/pull/252)
- RGB styling configuration [#249](https://github.com/geotrellis/geotrellis-server/issues/249)
- Add STAC Support [#263](https://github.com/geotrellis/geotrellis-server/pull/263)
- Fix ExtentRefication, wcs and wms default behavior when time is not specified for temporal layers [#278](https://github.com/geotrellis/geotrellis-server/pull/278)
- Expose STAC temporal metadata [#279](https://github.com/geotrellis/geotrellis-server/pull/279)
- Support for Time lists in WMS GetCapabilities [#259](https://github.com/geotrellis/geotrellis-server/issues/259)
- Default attribute should not be used to serve response inside Time extent [#260](https://github.com/geotrellis/geotrellis-server/issues/260)
- Enabling Time Dimension for mapalgebrasourceconf on Temporal Layers [#262](https://github.com/geotrellis/geotrellis-server/issues/262)

### Changed
- The `layers.layer-name.sources` field in application.conf is renamed to `source` and now supports a single RasterSource URI string. See `ogc-example/src/main/resources/application.conf` for examples. 
- `type = "simplesourceconf"` should be changed to `type = "rastersourceconf"` in application.conf
- Remove GeoTrellisRasterSourceLegacy [#197](https://github.com/geotrellis/geotrellis-server/issues/197)
- Receive GPG key while publishing artifacts [#271](https://github.com/geotrellis/geotrellis-server/pull/271)

### Fixed
- Addressed GeoTrellisRasterSourceLegacy issues and minimized number of RasterSource instances constructed for GeoTrellis Layers [#219](https://github.com/geotrellis/geotrellis-server/issues/219)
- Some source resolutions are sometimes skipped leading to reading too much tiles [#215](https://github.com/geotrellis/geotrellis-server/issues/215)
- LayerHistogram should select the CellSize large enough to compute the histogram [#261](https://github.com/geotrellis/geotrellis-server/pull/261)

## [4.1.0] - 2020-03-03

### Added
- Add support for an interpolated version of the color map [#161](https://github.com/geotrellis/geotrellis-server/issues/161)
- Generate WCS 1.1.1 protocol using XSD data model [#188](https://github.com/geotrellis/geotrellis-server/issues/188)
- WCS 1.1.1 GetCoverage Support [#192](https://github.com/geotrellis/geotrellis-server/issues/192)
- RasterSource Catalog [#162](https://github.com/geotrellis/geotrellis-server/issues/162)
- {WCS|WMTS|WMS}Model uses RasterSource catalog [#163](https://github.com/geotrellis/geotrellis-server/issues/163)
- WCS DescribeCoverage may include time TemporalDomain [#211](https://github.com/geotrellis/geotrellis-server/issues/211)
- WCS GetCoverage may include time param `TIMESEQUENCE` [#157](https://github.com/geotrellis/geotrellis-server/issues/157)
- WMS GetCapabilities may include time TemporalDomain [#185](https://github.com/geotrellis/geotrellis-server/issues/185)
- WMS GetMap may include time param `TIME` [#175](https://github.com/geotrellis/geotrellis-server/issues/175)

### Changed
- GT Server sources are now loaded via the GeoTrellis RasterSource SPI API. Each `sources` definition in your application.conf should be migrated to a list of [valid RasterSource SPI URIs](https://github.com/geotrellis/geotrellis-server/pull/222/commits/5937bf6022ba192eb8ab3a7cf28c6b08738fc56a) [#222](https://github.com/geotrellis/geotrellis-server/pull/222) 
- Included split dependencies a la GeoTrellis 3.2 for cats ecosystem libraries [\#184](https://github.com/geotrellis/geotrellis-server/pull/184)
- Dropped WCS 1.0.0 support
- Updated MAML up to 0.6.0 [#199](https://github.com/geotrellis/geotrellis-server/pull/199)
- Add ability to configure subset of OGC services [#151](https://github.com/geotrellis/geotrellis-server/issues/151)

### Fixed
- Use default styles appropriately when configured [#149](https://github.com/geotrellis/geotrellis-server/issues/149)
- Use linspace function to ensure correct interpolation of [#205](https://github.com/geotrellis/geotrellis-server/issues/205)
- SLF4J backends have been excluded and marked as Runtime dependencies as necessary to make logging work again [#205](https://github.com/geotrellis/geotrellis-server/issues/205)
- Fixed color interpolation bug related to constructing a range when the step is 0 [#111](https://github.com/geotrellis/geotrellis-server/issues/111)
- Non-integer (floating point) ColorMap keys now work with or without being quoted [#187](https://github.com/geotrellis/geotrellis-server/issues/187)
- Missing `<ows:Title>` and `<ows:Abstract>` elements in WCS GetCapabilities response [#114](https://github.com/geotrellis/geotrellis-server/issues/114) 
- Layer definition elements unused in WMS GetCapabilities response [#115](https://github.com/geotrellis/geotrellis-server/issues/115)
- Bad assembly strategy [#142](https://github.com/geotrellis/geotrellis-server/issues/142)

## [4.0.1] - 2019-11-22

### Changed
- Make publishSettings accessible to aggregate modules

## [4.0.0]- 2019-11-21

### Added
- Enable artifact publishing for `opengis`, `ogc`, and `stac` subprojects [\#147](https://github.com/geotrellis/geotrellis-server/pull/147)
- Included more link types based on OGC Features API [\#176](https://github.com/geotrellis/geotrellis-server/pull/176)
- Included more OGC specs (sld, se, wfs, filter) [#186](https://github.com/geotrellis/geotrellis-server/pull/186)

### Changed
- *Breaking* Update StacItem and StacLinkType compliance and better ergonomics with labeling extension [\#145](https://github.com/geotrellis/geotrellis-server/pull/145)
- *Breaking* Changed Bbox to an ADT [\#180](https://github.com/geotrellis/geotrellis-server/pull/180)
- Publish to Sonatype Nexus via CircleCI [#138](https://github.com/geotrellis/geotrellis-server/pull/138)
- Added `Collection` `rel` type to `StackLink` [#167](https://github.com/geotrellis/geotrellis-server/pull/167)
- Fixed collision with `decoder` method name in `circe-fs2` [#178](https://github.com/geotrellis/geotrellis-server/pull/178)
- *Breaking* Upgrade to GeoTrellis 3.1.0 [#182](https://github.com/geotrellis/geotrellis-server/pull/182)

### Fixed
- Fixed optionality and StacExtent de-/serialization based on a real live STAC [#179](https://github.com/geotrellis/geotrellis-server/pull/179)
- Fixed a bug in `LayerHistogram` sampling that prevented some histograms from being generated [\#167](https://github.com/geotrellis/geotrellis-server/pull/167)

## [3.4.0] - 2019-07-18
### Added
- Add support for RGB and RGBA tiffs [#137](https://github.com/geotrellis/geotrellis-server/pull/137)

## [3.3.8] - 2019-07-10
### Changed
- Update geotrellis-contrib [#135](https://github.com/geotrellis/geotrellis-server/pull/135)

[Unreleased]: https://github.com/geotrellis/geotrellis-server/compare/4.6.0...HEAD
[4.6.0]: https://github.com/geotrellis/geotrellis-server/compare/4.5.0...4.6.0
[4.5.0]: https://github.com/geotrellis/geotrellis-server/compare/4.4.0...4.5.0
[4.4.0]: https://github.com/geotrellis/geotrellis-server/compare/4.3.0...4.4.0
[4.3.0]: https://github.com/geotrellis/geotrellis-server/compare/4.2.0...4.3.0
[4.2.0]: https://github.com/geotrellis/geotrellis-server/compare/4.1.0...4.2.0
[4.1.0]: https://github.com/geotrellis/geotrellis-server/compare/4.0.1...4.1.0
[4.0.1]: https://github.com/geotrellis/geotrellis-server/compare/4.0.0...4.0.1
[4.0.0]: https://github.com/geotrellis/geotrellis-server/compare/3.4.0...4.0.0
[3.4.0]: https://github.com/geotrellis/geotrellis-server/compare/3.3.8...3.4.0
[3.3.8]: https://github.com/geotrellis/geotrellis-server/compare/3.3.7...3.3.8
