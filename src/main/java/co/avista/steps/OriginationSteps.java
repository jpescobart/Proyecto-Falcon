package co.avista.steps;

import co.avista.definitions.OriginationDefinition;
import co.avista.exceptions.ServiceException;
import co.avista.utils.DataGenerator;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;


public class OriginationSteps {
    private OriginationDefinition originationDefinition = new OriginationDefinition();

    @Given("^que tengo un cliente con unicamente un credito activo \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void queTengoUnClienteConUnicamenteUnCreditoActivo(String clientDocType, String clientDocNum, String codigoPagaduria, String nombrePagaduria, String payrollID, String numeroCredito) {
        originationDefinition.setClientID(clientDocType, clientDocNum);
        originationDefinition.setClientData(codigoPagaduria, nombrePagaduria, payrollID, numeroCredito);
    }


    @Given("^que tengo un cliente con unicamente un credito activo \"([^\"]*)\" \"([^\"]*)\"$")
    public void queTengoUnClienteConUnicamenteUnCreditoActivo(String clientDocType, String clientDocNum) {
        originationDefinition.setClientID(clientDocType, clientDocNum);
    }


    @Given("^que tengo un cliente con unicamente un credito finalizado \"([^\"]*)\" \"([^\"]*)\"$")
    public void queTengoUnClienteConUnicamenteUnCreditoFinalizado(String clientDocType, String clientDocNum) {
        originationDefinition.setClientID(clientDocType, clientDocNum);
        originationDefinition.setUnfindedClientData();
    }

    @Given("^que tengo un cliente potencial sin registrar$")
    public void queTengoUnClientePotencialSinRegistrar() {
        originationDefinition.setClientID("1", DataGenerator.generateRandomInt(8));
    }

    @When("^lo consulto usando el servicio de consultar prepago$")
    public void loConsultoUsandoElServicioDeConsultarPrepago() {
        originationDefinition.consumeClientAccountService();
    }

    @Then("^obtengo una aprobacion con la informacion del cliente$")
    public void obtengoUnaAprobacionConLaInformacionDelCliente() {
        originationDefinition.verifySucessClientAccountResponse();
    }

    @Then("^obtengo una respuesta de cliente no encontrado$")
    public void obtengoUnaRespuestaDeClienteNoEncontrado() {
        originationDefinition.verifyNotFoundClientResponse();
    }

    @Given("^que tengo un cliente con varios creditos activos y solo una pagaduria \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void queTengoUnClienteConVariosCreditosActivosYSoloUnaPagaduria(String tipoID, String numID, String codigoPagaduria, String nombrePagaduria, String payrollID, String numeroCredito1, String numeroCredito2) {
        originationDefinition.setClientID(tipoID, numID);
        originationDefinition.setClientData(codigoPagaduria, nombrePagaduria, payrollID, numeroCredito1);
        originationDefinition.addCredit(numeroCredito2, codigoPagaduria, nombrePagaduria, payrollID);
    }

    @Given("^que tengo un cliente con varios creditos activos de diferentes pagadurias \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void queTengoUnClienteConVariosCreditosActivosDeDiferentesPagadurias(String tipoID, String numID, String codigoPagaduria1, String nombrePagaduria1, String payrollID1, String numeroCredito1, String codigoPagaduria2, String nombrePagaduria2, String payrollID2, String numeroCredito2) {
        originationDefinition.setClientID(tipoID, numID);
        originationDefinition.setClientData(codigoPagaduria1, nombrePagaduria1, payrollID1, numeroCredito1);
        originationDefinition.addCredit(numeroCredito2, codigoPagaduria2, nombrePagaduria2, payrollID2);

    }

    @Then("^obtengo un consumo exitoso del servicio de consulta de credito en legado$")
    public void obtengoUnaConsumoExitosoDelServicioDeConsultaDeCreditoEnLegado() {
        originationDefinition.verifyServiceStatus(200);
    }

    @Then("^obtengo un consumo fallido del de servicio$")
    public void obtengoUnConsumoFallidoDelDeServicio() {
        originationDefinition.verifyServiceStatus(200);
    }


    //------------------------------Originacion----------------------------------------------

    @Given("^que creo un cliente nuevo con documentos \"([^\"]*)\",numero de cuenta, tipo de linbanza \"([^\"]*)\" y remanente \"([^\"]*)\" \"([^\"]*)\" inesistentes en mambu$")
    public void queCreoUnClienteNuevoConDocumentosNumeroDeCuentaTipoDeLinbanzaYRemanenteInesistentesEnMambu(String tipoID, String type, boolean lastPortfolioPurchase, int remainderAmount) throws Throwable {
        originationDefinition.postCreateOriginationDesembolso(tipoID,type,lastPortfolioPurchase,remainderAmount);
    }



    @When("^consulto en la base de datos  de falcon el id  de la cuenta para cliente creado$")
    public void consultoEnLaBaseDeDatosDeFalconElIdDeLaCuentaParaClienteCreado() {
        originationDefinition.conexionBaseDeDatosFalconcIdCuenta();
    }
    

    @And("^consulto la informacion en el servicio de mambu con el Id obtenido de base de datos$")
    public void consultoLaInformacionEnElServicioDeMambuConElIdObtenidoDeBaseDeDatos() {
        originationDefinition.getAccountholderKeyFalcon();
    }

    @Then("^el servicio de mambu clientes muestra la informacion correcta del cliente  cuando se ingreso el id holdenKey$")
    public void elServicioDeMambuClientesMuestraLaInformacionCorrectaDelClienteCuandoSeIngresoElIdHoldenKey() {
        originationDefinition.getConsultClientsMambu();
    }
    //-------------------------------------Remanente----------------------------

    @When("^consulto en la base de datos  de falcon el id  de la cuenta y el canal  para cliente creado$")
    public void consultoEnLaBaseDeDatosDeFalconElIdDeLaCuentaYElCanalParaClienteCreado() {
        originationDefinition.conexionBaseDeDatosFalconCanal();
    }


    @And("^realizo el proceso de desembolso masivo del remanente \"([^\"]*)\"$")
    public void realizoElProcesoDeDesembolsoMasivoDelRemanente(int remainderAmount) throws Throwable {
        originationDefinition.postDesembolsoMasivoRemanenteCliente(remainderAmount);
    }

    @Then("^el remanente es enviado al legado con los datos del cliente correctamente$")
    public void elRemanenteEsEnviadoAlLegadoConLosDatosDelClienteCorrectamente() {
        originationDefinition.consultarRemanenteBdLegado();
        originationDefinition.asserDesembolsoMasivoRemanente();
    }

    //-------------------------------Corto y largo plazo-----------------------------------

    @When("^consulto en  falcon el corto plazo y largo plazo de la cuenta para cliente creado$")
    public void consultoEnFalconElCortoPlazoYLargoPlazoDeLaCuentaParaClienteCreado() {
        originationDefinition.conexionBaseDeDatoscouchBaseCortoyLargoPlazo();

    }

    @Then("^guardo los datos  del credito en un archivo txt$")
    public void guardoLosDatosDelCreditoEnUnArchivoTxt() {
        originationDefinition.generateTxtAccount();
    }

    @Given("^que consulto el archivo txt con las cuentas creadas desde el servicio de originacion$")
    public void queConsultoElArchivoTxtConLasCuentasCreadasDesdeElServicioDeOriginacion() {
        originationDefinition.leerTxtAccounts();
    }


    @When("^ingreso a falcon y consulto por numero de cuenta el corto y largo plazo calculado$")
    public void ingresoAFalconYConsultoPorNumeroDeCuentaElCortoYLargoPlazoCalculado() {
        originationDefinition.consultarCalculoCortoyLargoPlazoFalcon();
    }

    @Then("^valido que el corto y largo plazo para las cuentas consultadas sea calculado correctamente en el legado$")
    public void validoQueElCortoYLargoPlazoParaLasCuentasConsultadasSeaCalculadoCorrectamenteEnElLegado() {
        originationDefinition.consultarCortoyLargoPlazoLegado();
        originationDefinition.asserCortoyLargoPlazoLegadoFalcon();
    }
 //-----------------------------------Entradas contables--------------------------------
     @When("^consulto en  falcon el registro de los clientes y creditos creados$")
     public void consultoEnFalconElRegistroDeLosClientesYCreditosCreados() {
        originationDefinition.conexionBaseDeDatoscouchBasenumeroCuentaEntradasContables();
 }

    @Then("^los creditos viajan al legado con las entradas contables definidas por mambu$")
    public void losCreditosViajanAlLegadoConLasEntradasContablesDefinidasPorMambu() throws InterruptedException {
        originationDefinition.consultarEntradasContablesLegado();
        originationDefinition.asserEntradascontablesLegadoFalcon();
    }

    //-----------------------------Causacion de intereses------------

    @Then("^guardo los datos  del credito en un archivo txt para luego consultarlos en el legado$")
    public void guardoLosDatosDelCreditoEnUnArchivoTxtParaLuegoConsultarlosEnElLegado() {
        originationDefinition.generateTxtCausacionDeIntereses();
    }


    @Given("^que consulto el archivo txt con las cuentas creadas desde el servicio de originacion con intereses pendientes$")
    public void queConsultoElArchivoTxtConLasCuentasCreadasDesdeElServicioDeOriginacionConInteresesPendientes() {
        originationDefinition.leerTxtAccountInteresesDiarios();
    }


    @When("^ingreso al  legado y consulto por  numero de cuenta los intereses generados$")
    public void ingresoAlLegadoYConsultoPorNumeroDeCuentaLosInteresesGenerados() {
        originationDefinition.consultarIntereDiarioLegado();
    }

    @Then("^valido que los intereses generados diaros para las cuentas consultadas sea calculado correctamente en el legado$")
    public void validoQueLosInteresesGeneradosDiarosParaLasCuentasConsultadasSeaCalculadoCorrectamenteEnElLegado() {
        originationDefinition.asserInteresesDiariosFalcon();
    }

    //------------------------------------------Pago sobre la couta----------------------------------------

    @Given("^que tengo una cuenta de credito para un cliente con tipo de docuemento \"([^\"]*)\" y una couta del credito pendiente por pagar$")
    public void queTengoUnaCuentaDeCreditoParaUnClienteConTipoDeDocuementoYUnaCoutaDelCreditoPendientePorPagar(String tipoID) throws Throwable {
        originationDefinition.postCreateOriginationPagoSobreCouta(tipoID);


    }

    @And("^consulto en  falcon el registro de los clientes y creditos creados con coutas pendientes$")
    public void consultoEnFalconElRegistroDeLosClientesYCreditosCreadosConCoutasPendientes() {
        originationDefinition.conexionBaseDeDatosFalconcIdCuenta();
    }

    @When("^consulto en  mambu el calendario de pagos para validar la couta pendiente por pagar$")
    public void consultoEnMambuElCalendarioDePagosParaValidarLaCoutaPendientePorPagar() {
        originationDefinition.getConsultCalendarioMambu();

    }

    @Then("^guardo los datos del credito  y las cuentas pendientes por pagar en un archivo txt$")
    public void guardoLosDatosDelCreditoYLasCuentasPendientesPorPagarEnUnArchivoTxt() {
        originationDefinition.generateTxtAccountPagoSobreLacouta();
    }
    

    @When("^consulto el archivo txt con las cuentas pendientes por pagar para cada cuenta de credito$")
    public void consultoElArchivoTxtConLasCuentasPendientesPorPagarParaCadaCuentaDeCredito() {
        originationDefinition.leerTxtAccountsPagoCouta();
    }

    @Then("^valido que las coutas pendientes por pagar tengan el calculo del seguro correctamente$")
    public void validoQueLasCoutasPendientesPorPagarTenganElCalculoDelSeguroCorrectamente() {
        originationDefinition.getConsultCalendarioMambuPagoPrimeraCuota();
    }


    //------------------------------------------carteta vendida-----------------------------------------

    @And("^ejecuto el servicio de  marcacion de cartera vendida para la cuenta en especifico$")
    public void ejecutoElServicioDeMarcacionDeCarteraVendidaParaLaCuentaEnEspecifico() {
        originationDefinition.postCreateAccountCarteraVendida();
    }

    @And("^consulto en falco que la cuenta creada este marcada como cartera vendida$")
    public void consultoEnFalcoQueLaCuentaCreadaEsteMarcadaComoCarteraVendida() {
        originationDefinition.conexionBaseDeDatoscouchCarteraVendida();

    }

    @Then("^guardo los datos  del credito en un archivo txt para luego consultarlos en el legado con la cartera vendida$")
    public void guardoLosDatosDelCreditoEnUnArchivoTxtParaLuegoConsultarlosEnElLegadoConLaCarteraVendida() {
        originationDefinition.generateTxtCausacionDeInteresesCarteraVendida();
    }

    @Given("^que consulto el archivo txt con las cuentas vendidas creadas desde el servicio de originacion con intereses pendientes$")
    public void queConsultoElArchivoTxtConLasCuentasVendidasCreadasDesdeElServicioDeOriginacionConInteresesPendientes() {
        originationDefinition.leerTxtAccountInteresesDiariosCarteraVendida();
    }

    @When("^ingreso al  legado y consulto por  numero de cuenta los intereses generados para las cuentas con cartera vendida$")
    public void ingresoAlLegadoYConsultoPorNumeroDeCuentaLosInteresesGeneradosParaLasCuentasConCarteraVendida() {
        originationDefinition.consultarIntereDiarioLegadoCarteraVendida();
    }

    @Then("^valido que los intereses generados diaros para las cuentas de cartera vendida consultadas sea calculado correctamente en el legado$")
    public void validoQueLosInteresesGeneradosDiarosParaLasCuentasDeCarteraVendidaConsultadasSeaCalculadoCorrectamenteEnElLegado() {
        originationDefinition.asserInteresesDiariosCarteraVendidaFalcon();
    }

    //------------------------------------Cartera propia en mora-----------------

    @When("^consulto en  falcon el registro de los clientes y creditos creados en mora$")
    public void consultoEnFalconElRegistroDeLosClientesYCreditosCreadosEnMora() {
        originationDefinition.conexionBaseDeDatoscouchBasenumeroCuentaEntradasContables();
    }

    @Given("^que creo un cliente nuevo con documentos \"([^\"]*)\",numero de cuenta, tipo de libranza \"([^\"]*)\"  y  con intereses en mora  en mambu$")
    public void queCreoUnClienteNuevoConDocumentosNumeroDeCuentaTipoDeLibranzaYConInteresesEnMoraEnMambu(String tipoID, String type) throws Throwable {
        originationDefinition.postCreateOriginationDesembolsoCuentaEnMora(tipoID,type);

    }

    @Then("^guardo los datos  del credito  en mora en un archivo txt para luego consultarlos en el legado$")
    public void guardoLosDatosDelCreditoEnMoraEnUnArchivoTxtParaLuegoConsultarlosEnElLegado() {
        originationDefinition.generateTxtCausacionDeInteresesEnMora();
    }

    @Given("^que consulto el archivo txt con las cuentas creadas desde el servicio de originacion con intereses en mora pendientes$")
    public void queConsultoElArchivoTxtConLasCuentasCreadasDesdeElServicioDeOriginacionConInteresesEnMoraPendientes() {
        originationDefinition.leerTxtAccountInteresesDiariosEnMora();
    }

    @When("^ingreso al  legado y consulto por  numero de cuenta los intereses en mora  generados$")
    public void ingresoAlLegadoYConsultoPorNumeroDeCuentaLosInteresesEnMoraGenerados() {
        originationDefinition.consultarIntereDiarioLegado();
    }

    @Then("^valido que los intereses generados  en mora diaros para las cuentas consultadas sea calculado correctamente en el legado$")
    public void validoQueLosInteresesGeneradosEnMoraDiarosParaLasCuentasConsultadasSeaCalculadoCorrectamenteEnElLegado() {
        originationDefinition.asserInteresesDiariosEnMoraFalcon();
    }

    //------------------------------------Cartera vendida en mora-------------------------------------------

    @When("^consulto en falcon el registro de los clientes y creditos vendidos creados en mora$")
    public void consultoEnFalconElRegistroDeLosClientesYCreditosVendidosCreadosEnMora() throws IOException {
        originationDefinition.obtenerTokenCouchBase();
        originationDefinition.actualizarCarteraVendida();
    }

    @Then("^guardo los datos del credito vendido en mora en un archivo txt para luego consultarlos en el legado$")
    public void guardoLosDatosDelCreditoVendidoEnMoraEnUnArchivoTxtParaLuegoConsultarlosEnElLegado() {
        originationDefinition.generateTxtCausacionDeInteresesCarteraVendidaEnMora();
    }

    @Given("^que consulto el archivo txt con las cuentas  vendidas creadas desde el servicio de originacion con intereses en mora pendientes$")
    public void queConsultoElArchivoTxtConLasCuentasVendidasCreadasDesdeElServicioDeOriginacionConInteresesEnMoraPendientes() {
        originationDefinition.leerTxtAccountInteresesCarteraVendidaEnMora();
    }

    @When("^ingreso al  legado y consulto por  numero de cuenta los intereses cartera vendida  en mora  generados$")
    public void ingresoAlLegadoYConsultoPorNumeroDeCuentaLosInteresesCarteraVendidaEnMoraGenerados() {
        originationDefinition.consultarIntereDiarioLegadoCarteraVendidaEnMora();
    }

    @Then("^valido que los intereses generados  en mora  para las cuentas vendidas consultadas, sean calculados correctamente en el legado$")
    public void validoQueLosInteresesGeneradosEnMoraParaLasCuentasVendidasConsultadasSeanCalculadosCorrectamenteEnElLegado() {
        originationDefinition.asserInteresesDiariosEnMoraCarteraVendidaFalcon();
    }
}

