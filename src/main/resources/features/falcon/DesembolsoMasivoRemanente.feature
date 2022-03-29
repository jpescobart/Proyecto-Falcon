Feature: HU-0001-Desembolso Masivo del Remanente al cliente
  Yo Como: Falcon.
  Quiero: Disparar el desembolso del remanente al cliente cuando se reciba la notificación desde la transacción BBVA Cash.
  Para: Poder entregar los recursos al cliente cuando el área de desembolsos ya ha realizado las validaciones del caso.

  @HU-0001 @DESEMBOLSOMASIVO @FALCON_Pruebas
  Scenario Outline: Consultar en el legado el remanate  desembolsado desde el servicio de  desembolso masivo
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de linbanza "<Type>" y remanente "<lastPortfolioPurchase>" "<remainderAmount>" inesistentes en mambu
    When consulto en la base de datos  de falcon el id  de la cuenta y el canal  para cliente creado
    And  realizo el proceso de desembolso masivo del remanente "<remainderAmount>"
    Then el remanente es enviado al legado con los datos del cliente correctamente
    Examples:
      | tipoID | Type |lastPortfolioPurchase| remainderAmount|
      | CC     | CC   |        true         | 2000000        |
      | CC     | CC   |        true         | 2000000        |
      | CC     | CC   |        true         | 2000000        |
      | CC     | CC   |        True         | 2000000        |
      | CC     | CC   |        True         | 2000000        |
      | CC     | CC   |        True         | 2000000        |
      | CC     | R    |        False        | 0              |
      | CC     | R    |        False        | 0              |
      | CC     | R    |        False        | 0              |
      | CC     | R    |        False        | 0              |