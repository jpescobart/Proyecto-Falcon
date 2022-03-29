Feature: HU-0001-Efectuar cargo de seguro sobre la couta N
  Yo Como: Equipo Voyager
  Quiero: Realizar los refactors necesarios sobre la base del proyecto para implementar los cronjobs de  seguros obligatorios
  Para:  incrustar la prima de seguro obligatorio cada vez que se vuelva exigible una cuota en cada una de las cuotas de los créditos de libranza.



  @HU-0001 @CARGOSOBRELACOUTA @FALCON
  Scenario Outline: Cargar el valor del seguro sobre la  proxima  couta a cancelar
    Given  que tengo una cuenta de credito para un cliente con tipo de docuemento "<tipoID>" y una couta del credito pendiente por pagar
    When consulto en  falcon el registro de los clientes y creditos creados con coutas pendientes
    And consulto en  mambu el calendario de pagos para validar la couta pendiente por pagar
    Then guardo los datos del credito  y las cuentas pendientes por pagar en un archivo txt
    Examples:
      | tipoID |
      | CC     |
      | CC     |
      | CC     |
      | CC     |
      | CC     |
      | CC     |
      | CC     |
      | CC     |
      | CC     |
      | CC     |

  @HU-0001 @CARGOSOBRELACOUTA @FALCONLEGADO
  Scenario: Obtener los valores  del seguro en la primera couta pendiente por pagar para cada cuenta
    When  consulto el archivo txt con las cuentas pendientes por pagar para cada cuenta de credito
    Then  valido que las coutas pendientes por pagar tengan el calculo del seguro correctamente

