Feature: Backlog Item 755 - Informe descuento pensionados-beneficiario proteccion para la casuistica de sobrevivencia
YO como alianzas estratégicas
QUIERO poder notificarle a protección el descuento que se aplico para el crédito solicitado por el beneficiario
PARA que la pagaduría tenga la información actualizada

@ALLIES @PROTECCION_SOBREVIVENCIA
Scenario Outline: Informe de descuento a beneficirio de proteccion desde el mes actual
Given que tengo un cliente potencial beneficiario en proteccion "<tipoIDBeneficiario>" "<numIDBeneficiario>"
When informo a proteccion un descuento por pago de prestamo a partir del mes actualpara el beneficiario "<tipoIDPensionado>" "<numIDPensionado>""<tipoIDBeneficiario>" "<numIDBeneficiario>"
Then el descuento informado es visible en los descuentos del beneficiario "<tipoIDBeneficiario>" "<numIDBeneficiario>"
Examples:
|tipoIDPensionado |numIDPensionado  |tipoIDBeneficiario |numIDBeneficiario|
|CC               |1144049760       |CC                 |31406377       |
|CC               |1144081449       |CC                 |31537402       |
|CC               |1144129895       |CC                 |31890341       |

