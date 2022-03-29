Feature: HU-0001-Yo Como: Equipo Voyager.
  Quiero : Llevar a cabo la integración satisfactoria con el servicio construido por el sistema Legado.
  Para: Garantizar la óptima recepción en el sistema legado de los datos generados en Mambu y así poder asegurar que la
  causacion de intereses diarios se registre correctamente.


  @HU-0001 @CAUSACIONDEINTERESESCA @FALCON
  Scenario Outline: enviar los valores  de la causacion de intereses para un credito en espacifico al sistema legado
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de linbanza "<Type>" y remanente "<lastPortfolioPurchase>" "<remainderAmount>" inesistentes en mambu
    When consulto en  falcon el registro de los clientes y creditos creados
    Then guardo los datos  del credito en un archivo txt para luego consultarlos en el legado
    Examples:
      | tipoID | Type |lastPortfolioPurchase| remainderAmount|
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |



  @HU-0001 @CAUSACIONDEINTERESESCA @FALCONLEGADO
  Scenario: Obtener los valores de la causacion de intereses diarios  de un credito desembolsado  para un cliente en espacifico.
    Given que consulto el archivo txt con las cuentas creadas desde el servicio de originacion con intereses pendientes
    When ingreso al  legado y consulto por  numero de cuenta los intereses generados
    Then valido que los intereses generados diaros para las cuentas consultadas sea calculado correctamente en el legado



  @HU-0001 @CAUSACIONDEINTERESESCI @FALCON
  Scenario Outline: enviar los valores  de la causacion de intereses  cartera vendida para un credito en espacifico al sistema legado
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de linbanza "<Type>" y remanente "<lastPortfolioPurchase>" "<remainderAmount>" inesistentes en mambu
    When consulto en  falcon el registro de los clientes y creditos creados
    And ejecuto el servicio de  marcacion de cartera vendida para la cuenta en especifico
    And consulto en falco que la cuenta creada este marcada como cartera vendida
    Then guardo los datos  del credito en un archivo txt para luego consultarlos en el legado con la cartera vendida
    Examples:
      | tipoID | Type |lastPortfolioPurchase| remainderAmount|
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |
      | CC     | CC   |        False        | 0              |



  @HU-0001 @CAUSACIONDEINTERESESCI @FALCONLEGADO
  Scenario: Obtener los valores de la causacion de intereses cartera vendida de un credito desembolsado  para un cliente en espacifico.
    Given que consulto el archivo txt con las cuentas vendidas creadas desde el servicio de originacion con intereses pendientes
    When ingreso al  legado y consulto por  numero de cuenta los intereses generados para las cuentas con cartera vendida
    Then valido que los intereses generados diaros para las cuentas de cartera vendida consultadas sea calculado correctamente en el legado


  @HU-0001 @MARCACIONCARTERAVENDIDA @FALCONSMOKE
  Scenario Outline: Verificacion de marcacion cartera vendida
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de linbanza "<Type>" y remanente "<lastPortfolioPurchase>" "<remainderAmount>" inesistentes en mambu
    When  ejecuto el servicio de  marcacion de cartera vendida para la cuenta en especifico
    Then consulto en falco que la cuenta creada este marcada como cartera vendida
    Examples:
      | tipoID | Type |lastPortfolioPurchase| remainderAmount|
      | CC     | CC   |        False        | 0              |




  @HU-0001 @CAUSACIONDEINTERESESMORACA @FALCON
  Scenario Outline: enviar los valores  de la causacion de intereses en mora para un credito en espacifico al sistema legado
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de libranza "<Type>"  y  con intereses en mora  en mambu
    When consulto en  falcon el registro de los clientes y creditos creados en mora
    Then guardo los datos  del credito  en mora en un archivo txt para luego consultarlos en el legado
    Examples:
      | tipoID | Type |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |



  @HU-0001 @CAUSACIONDEINTERESESMORACA @FALCONLEGADO
  Scenario: Obtener los valores de la causacion de intereses diarios en mora  de un credito desembolsado  para un cliente en espacifico.
    Given que consulto el archivo txt con las cuentas creadas desde el servicio de originacion con intereses en mora pendientes
    When ingreso al  legado y consulto por  numero de cuenta los intereses en mora  generados
    Then valido que los intereses generados  en mora diaros para las cuentas consultadas sea calculado correctamente en el legado






  @HU-0001 @CAUSACIONDEINTERESESMORACI @FALCON
  Scenario Outline: enviar los valores  de la causacion de intereses en mora para un credito vendido al sistema legado
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de libranza "<Type>"  y  con intereses en mora  en mambu
    When consulto en falcon el registro de los clientes y creditos vendidos creados en mora
    Then guardo los datos del credito vendido en mora en un archivo txt para luego consultarlos en el legado
    Examples:
      | tipoID | Type |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |
      | CC     | CC   |



  @HU-0001 @CAUSACIONDEINTERESESMORACI @FALCONLEGADO
  Scenario: Obtener los valores de la causacion de intereses diarios en mora  de un credito vendido desembolsado  para un cliente en espacifico.
    Given que consulto el archivo txt con las cuentas  vendidas creadas desde el servicio de originacion con intereses en mora pendientes
    When ingreso al  legado y consulto por  numero de cuenta los intereses cartera vendida  en mora  generados
    Then valido que los intereses generados  en mora  para las cuentas vendidas consultadas, sean calculados correctamente en el legado