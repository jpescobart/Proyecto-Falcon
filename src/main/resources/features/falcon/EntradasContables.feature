Feature: HU-0001-Yo Como: Equipo Voyager.
  Quiero : Llevar a cabo la integración satisfactoria con el servicio construido por el sistema Legado.
  Para: Garantizar la óptima recepción en el sistema legado de los datos generados en Mambu y así poder asegurar que las entradas contables
  lleguen al sistema AWA De forma correcta.



  @HU-0001 @ENTRADASCONTABLES @FALCON_pruebas
  Scenario Outline: enviar los valores de corto  plazo y largo plazo de un credito desembolsado  para un cliente en espacifico.
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de linbanza "<Type>" y remanente "<lastPortfolioPurchase>" "<remainderAmount>" inesistentes en mambu
    When consulto en  falcon el registro de los clientes y creditos creados
    Then los creditos viajan al legado con las entradas contables definidas por mambu
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
      | CC     | CC   |        False        | 0              |