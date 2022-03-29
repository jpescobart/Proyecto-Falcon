Feature: HU-675-consumir servicio de originacion
  Yo Como: Equipo Voyager
  Quiero: Una comunicación entre el sistema en donde se realizan las autorizaciones de compras de cartera
  y se desembolsan los créditos (BBVA CASH)  en el legado y Falcon.
  Para: Poder Obtener los datos requeridos del legado para proceder con la creación de la parte del crédito
  de libranza que corresponde a LIBRE INVERSION Y COMPRA DE CARTERA asociado a ese cliente en Falcon.


  @HU-675 @ORIGINACIONCOMPRACARTERA @FALCON
  Scenario Outline: Obtener los datos requeridos del legado para proceder con la creación de la parte del crédito de libranza correspondiente a compra de cartera CC
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de linbanza "<Type>" y remanente "<lastPortfolioPurchase>" "<remainderAmount>" inesistentes en mambu
    When consulto en la base de datos  de falcon el id  de la cuenta para cliente creado
    And  consulto la informacion en el servicio de mambu con el Id obtenido de base de datos
    Then el servicio de mambu clientes muestra la informacion correcta del cliente  cuando se ingreso el id holdenKey
    Examples:
      | tipoID | Type |lastPortfolioPurchase| remainderAmount|
      | CC     | CC   |        true         | 2000000        |
      | CC     | CC   |        true         | 2000000        |
      | CC     | CC   |        true         | 2000000        |
      | CC     | CC   |        True         | 2000000        |
      | CC     | CC   |        True         | 2000000        |
      | CC     | CC   |        True         | 2000000        |

  @HU-675 @ORIGINACIONLIBREINVERSION @FALCON
  Scenario Outline: Obtener los datos requeridos del legado para proceder con la creación de la parte del crédito de libranza correspondiente a libre inversion R
    Given que creo un cliente nuevo con documentos "<tipoID>",numero de cuenta, tipo de linbanza "<Type>" y remanente "<lastPortfolioPurchase>" "<remainderAmount>" inesistentes en mambu
    When consulto en la base de datos  de falcon el id  de la cuenta para cliente creado
    And  consulto la informacion en el servicio de mambu con el Id obtenido de base de datos
    Then el servicio de mambu clientes muestra la informacion correcta del cliente  cuando se ingreso el id holdenKey
    Examples:
      | tipoID | Type |lastPortfolioPurchase| remainderAmount|
      | CC     | R    |        false        | 0              |
      | CC     | R    |        false        | 0              |
      | CC     | R    |        false        | 0              |
      | CC     | R    |        false        | 0              |
      | CC     | R    |        false        | 0              |
      | CC     | R    |        false        | 0              |