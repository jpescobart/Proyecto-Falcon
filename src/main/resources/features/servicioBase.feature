#language:es
Característica: Prueba base de servicio

  @Purchase
    Escenario: Ejemplo consumo servicio rest resultado exitoso
    Dado que deseo consultar el servicio get de prueba
    Cuando realizo una consulta
    Entonces obtengo una respuesta de consulta exitosa

  @Purchase
  Escenario: Ejemplo consumo servicio rest resultado fallido
    Dado que deseo consultar el servicio get de prueba
    Cuando realizo una consulta
    Entonces obtengo una respuesta de consulta fallido