Feature: Backlog Item 433 - Informe descuento pensionados proteccion
  YO como alianzas estratégicas
  QUIERO poder notificarle a protección el descuento que se aplico para el crédito solicitado por el pensionado
  PARA que la pagaduría tenga la información actualizada

  @ALLIES @PROTECCION
  Scenario Outline: Informe de descuento a pensionado de proteccion desde el mes actual
    Given que tengo un cliente potencial pensionado en proteccion "<tipoID>" "<numID>"
    When informo a proteccion un descuento por pago de prestamo a partir del mes actual "<tipoID>" "<numID>"
    Then el descuento informado es visible en los descuentos del pensionado "<tipoID>" "<numID>"
    Examples:
      |tipoID |numID      |
      |CC     |12527058   |
      |CC     |98558608   |
      |CC     |3614565    |

  @ALLIES @PROTECCION
  Scenario Outline: Informe de descuento a pensionado superior a su mesada
    Given que tengo un cliente potencial pensionado en proteccion "<tipoID>" "<numID>"
    When informo a proteccion un descuento por pago de prestamo superior a la mesada "<tipoID>" "<numID>"
    Then obtengo que el descuento no se puede realizar
    Examples:
      |tipoID |numID      |
      |CC     |41564300   |


  @ALLIES @PROTECCION @PROTECCIONSMOKE
  Scenario: Informe de descuento a pensionado inexistente
    When informo a proteccion un descuento por pago de prestamo de un cliente inexistente
    Then obtengo que la persona no existe en proteccion

  @ALLIES @PROTECCION @PROTECCIONSMOKE
  Scenario Outline: Verificacion de descuento a pensionado de proteccion desde el mes actual
    Given que tengo un cliente potencial pensionado en proteccion "<tipoID>" "<numID>"
    When informo a proteccion un descuento por pago de prestamo a partir del mes actual "<tipoID>" "<numID>"
    Then obtengo un consumo exitoso del servicio de informe de descuento a pensionado proteccion
    Examples:
      |tipoID |numID      |
      |CC     |12527058   |