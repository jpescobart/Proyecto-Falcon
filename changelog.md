# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

[major.minor.revision]
- major: Versión principal; conjunto de funcionalidades concretas que son recogidas y cubiertas en dicha versión.
- minor: Funcionalidad menor cubierta en la versión de software entregada.
- revision: Se modifican cuando hay revisiones de código ante fallos.

## [Pending List]
- Implementación de archivo de reporte.
- Implementación de ejemplo que deserialice response a modelo.
- Utilidad de generación automática de datos.
- Utilidad de manejo de json y serializador/deserializador.
- Externalización de aserciones.

## [0.1.0] - 2020-07-24
### Changed
- NA
### Removed
- NA
### Added
- Creación del proyecto para automatización de servicios. 
    - Consumos basados en Rest assured.
    - Hamcrest para aserciones.
    - Cucumber para descripción de escenarios en BDD.
- Creación de cliente para consumo de servicios, inicialmente configurado para servicios REST, POST y PUT.
- Creación de template para creación de servicios, basandose en patrón de service object.
- Creación de 2 escenarios de ejemplo para servicio REST, uno exitoso y uno fallido.
