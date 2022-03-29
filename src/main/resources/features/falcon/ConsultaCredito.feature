Feature: HU-0001-Consulta de Creditos por Cliente
    Yo como usuario del proceso de estudio de credito​
    Quiero consultar la cuenta de libranza (credito activo) en el core Banking.
    Para saber si habra retanqueo.

    @HU-0001 @ORIGINACION @FALCON_pruebas
    Scenario Outline: Consulta de cliente con crédito de libranza activo
        Given que tengo un cliente con unicamente un credito activo "<tipoID>" "<numID>" "<codigoPagaduria>" "<nombrePagaduria>" "<payrollID>" "<numeroCredito>"
        When lo consulto usando el servicio de consultar prepago
        Then obtengo una aprobacion con la informacion del cliente
    Examples:
    |tipoID|numID    |codigoPagaduria|nombrePagaduria                                      |payrollID   |numeroCredito|
    |CC    |8287870  |503            |COLPENSIONES                                         |908287870100|77202016206  |
    |CC    |24309462 |503            |COLPENSIONES                                         |924309462000|772019857    |
    |CC    |22688119 |503            |COLPENSIONES                                         |922688119100|7720206768   |
    |CC    |28352315 |606            |SECRETARIA DE EDUCACION DEPARTAMENTAL DE SANTANDER   |            |7720209635   |
    |CC    |52600944 |663            |Secretaria de Educacion de Cundinamarca              |            |7720196429   |
    |CC    |41905218 |R31            |SECRETARIA EDU. MUNICIPIO ARMENIA                    |            |77202013102  |
    |CC    |23810756 |916            |SECRETARIA DE EDUCACION DEL DISTRITO BOGOTA ACTIVOS  |            |7720209548   |



    @HU-0001 @ORIGINACION @FALCON_pruebas
    Scenario Outline: Consulta de cliente con crédito de libranza finalizado
        Given que tengo un cliente con unicamente un credito finalizado "<tipoID>" "<numID>"
        When lo consulto usando el servicio de consultar prepago
        Then obtengo una respuesta de cliente no encontrado
    Examples:
    |tipoID|numID   |
    |CC    |23132   |
    |CC    |57300385|
    |CC    |12525337|
    |CC    |43588183|
    |CC    |4179412 |

    @HU-0001 @ORIGINACION @FALCON_pruebas
    Scenario: Consulta de un cliente nuevo
        Given que tengo un cliente potencial sin registrar
        When lo consulto usando el servicio de consultar prepago
        Then obtengo una respuesta de cliente no encontrado

    @HU-0001 @ORIGINACION @FALCON_pruebas
    Scenario Outline: Consulta prepago de cliente con varios creditos de libranza activos de la misma pagaduria
        Given que tengo un cliente con varios creditos activos y solo una pagaduria "<tipoID>" "<numID>" "<codigoPagaduria>" "<nombrePagaduria>" "<payrollID>" "<numeroCredito1>" "<numeroCredito2>"
        When lo consulto usando el servicio de consultar prepago
        Then obtengo una aprobacion con la informacion del cliente
        Examples:
            |tipoID|numID   |codigoPagaduria|nombrePagaduria                                    |payrollID      |numeroCredito1|numeroCredito2|
            |CC    |6752707 |503            |COLPENSIONES                                       |906752707000   |7720196019    |7720207159    |
            |CC    |13360630|612            |CAJA DE SUELDOS DE RETIRO DE POLICIA NACIONAL      |               |7720192474    |77202014251   |
            |CC    |63281171|916            |SECRETARIA DE EDUCACION DEL DISTRITO BOGOTA ACTIVOS|               |7720195507    |7720195510    |
            |CC    |6380375 |503            |COLPENSIONES                                       |906380375100   |7720208562    |7720208563    |

    @HU-0001 @ORIGINACION @FALCON_pruebas
    Scenario Outline: Consulta de cliente con varios creditos de libranza de diferentes pagadurias
        Given que tengo un cliente con varios creditos activos de diferentes pagadurias "<tipoID>" "<numID>" "<codigoPagaduria1>" "<nombrePagaduria1>" "<payrollID1>" "<numeroCredito1>" "<codigoPagaduria2>" "<nombrePagaduria2>" "<payrollID2>" "<numeroCredito2>"
        When lo consulto usando el servicio de consultar prepago
        Then obtengo una aprobacion con la informacion del cliente
        Examples:
            |tipoID|numID    |codigoPagaduria1|nombrePagaduria1                                    |payrollID1   |numeroCredito1|codigoPagaduria2|nombrePagaduria2                                    |payrollID2      |numeroCredito2|
            |CC    |42486584 |501             |FIDUCIARIA LA PREVISORA                             |             |7720195856    |503             |COLPENSIONES                                        |160025128103    |7720207159    |
            |CC    |24472039 |551             |ALCALDIA ARMENIA PENSIONADOS                        |             |7720192107    |503             |COLPENSIONES                                        |924472039000    |772019421     |
            |CC    |6865501  |501             |FIDUCIARIA LA PREVISORA                             |             |7720191734    |503             |COLPENSIONES                                        |906865501100    |772019844     |
            |CC    |33130941 |501             |FIDUCIARIA LA PREVISORA                             |             |7720196056    |503             |COLPENSIONES                                        |903547883150    |77202012978   |
            |CC    |5587001  |503             |COLPENSIONES                                        |905587001100 |7720207197    |502             |FONDO DE PENSIONES PUBLICAS NIVEL NACIONAL          |                |7720192159    |

    @HU-0001 @FALCONSMOKE
    Scenario Outline: Verificacion de consulta de persona existente
        Given que tengo un cliente con unicamente un credito activo "<tipoID>" "<numID>"
        When lo consulto usando el servicio de consultar prepago
        Then obtengo un consumo exitoso del servicio de consulta de credito en legado
        Examples:
            |tipoID|numID   |
            |CC    |6752707 |

    @HU-0001 @FALCONSMOKE
    Scenario Outline: Verificacion de consulta de persona inexistente
        Given que tengo un cliente con unicamente un credito activo "<tipoID>" "<numID>"
        When lo consulto usando el servicio de consultar prepago
        Then obtengo una respuesta de cliente no encontrado
        Examples:
            |tipoID|numID     |
            |CC    |675270712 |