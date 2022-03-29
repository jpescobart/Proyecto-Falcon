Feature: HU-0001-Efectuar la Clasificación de la Cartera en CP y LP
  Yo Como: Equipo Voyager
  Quiero: Implementar un componente.
  Para: Realizar la clasificación en CP y LP. (Cartera de Corto Plazo y Cartera de Largo Plazo)



  @HU-0001 @CPyLP @FALCON
  Scenario Outline: enviar los valores de corto  plazo y largo plazo de un credito desembolsado  para un cliente en espacifico.
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de linbanza "<Type>" y remanente "<lastPortfolioPurchase>" "<remainderAmount>" inesistentes en mambu
    When consulto en  falcon el corto plazo y largo plazo de la cuenta para cliente creado
    Then guardo los datos  del credito en un archivo txt
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


  @HU-0001 @CPyLP @FALCONLEGADO
  Scenario: Obtener los valores de corto  plazo y largo plazo de un credito desembolsado  para un cliente en espacifico.
    Given que consulto el archivo txt con las cuentas creadas desde el servicio de originacion
    When ingreso a falcon y consulto por numero de cuenta el corto y largo plazo calculado
    Then valido que el corto y largo plazo para las cuentas consultadas sea calculado correctamente en el legado