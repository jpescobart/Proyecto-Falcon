Feature: Backlog Item 423 - Consulta pensionados proteccion
  YO como alianzas estratégicas
  QUIERO consultar la informacion de un pensionado en protección
  PARA calcular la capacidad de endeudamiento de dicho pensionado

  @ALLIES @PROTECCION
  Scenario Outline: Consulta de un pensionado de proteccion
    Given que tengo un cliente potencial pensionado en proteccion "<tipoID>" "<numID>"
    When consulto sus datos usando el servicio de consulta de pensionados proteccion "<tipoID>" "<numID>"
    Then obtengo los datos obtenidos corresponden a los que tiene la AFP
    Examples:
      |tipoID |numID     |
      |CC     |5836120   |
      |CC     |32406268  |
      |CE     |113432    |
      |CC     |8257386   |
      |CC     |32406268  |
      |CC     |70550482  |
      |CC     |70060508  |

  @ALLIES @PROTECCION
  Scenario Outline: Consulta de un pensionado sin descuentos
    Given que tengo un cliente potencial pensionado en proteccion "<tipoID>" "<numID>"
    When consulto sus datos usando el servicio de consulta de pensionados proteccion "<tipoID>" "<numID>"
    Then obtengo los datos obtenidos corresponden a los que tiene la AFP
    And el cliente no registra descuentos
    Examples:
      |tipoID |numID    |
      |CC     |8715545  |
      |CC     |8287343  |
      |CC     |8246122  |

  @ALLIES @PROTECCION
  Scenario Outline: Verificacion de diferentes tipos de pension
    Given que tengo un cliente potencial pensionado en proteccion "<tipoID>" "<numID>"
    When consulto sus datos usando el servicio de consulta de pensionados proteccion "<tipoID>" "<numID>"
    Then obtengo los datos obtenidos corresponden a los que tiene la AFP
    And el cliente tiene el tipo de pension esperada "<tipoPension>"
    Examples:
      |tipoID |numID      |tipoPension    |
      |CC     |5289948    |INVALIDEZ      |
      |CC     |72158592   |INVALIDEZ      |
      |TI     |1020112511 |SOBREVIVENCIA  |
      |CC     |4319447    |VEJEZ          |
      |CC     |6093946    |VEJEZ          |

  @ALLIES @PROTECCION @PROTECCIONSMOKE
  Scenario: Consulta de un pensionado inexistente
    When consulto los datos de un pensionado inexistente
    Then obtengo que la persona no existe en proteccion

  @HU-0001 @PROTECCIONSMOKE
  Scenario Outline: Verificacion de consulta de persona existente
    Given que tengo un cliente potencial pensionado en proteccion "<tipoID>" "<numID>"
    When consulto sus datos usando el servicio de consulta de pensionados proteccion "<tipoID>" "<numID>"
    Then obtengo un consumo exitoso del servicio de consulta de pensionado proteccion
    Examples:
      |tipoID|numID   |
      |CC    |5836120 |