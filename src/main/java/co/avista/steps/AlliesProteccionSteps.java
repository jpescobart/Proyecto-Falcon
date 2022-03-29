package co.avista.steps;

import co.avista.definitions.AlliesProteccionDefinition;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AlliesProteccionSteps {
    private final AlliesProteccionDefinition alliesProteccionDefinition = new AlliesProteccionDefinition();

    @Given("^que tengo un cliente potencial pensionado en proteccion \"([^\"]*)\" \"([^\"]*)\"$")
    public void queTengoUnClientePotencialPensionadoEnProteccion(String docType, String docNum) {
        alliesProteccionDefinition.getPensionerDataFromProteccion(docType,docNum);
    }


    @When("^consulto sus datos usando el servicio de consulta de pensionados proteccion \"([^\"]*)\" \"([^\"]*)\"$")
    public void consultoSusDatosUsandoElServicioDeConsultaDePensionadosProteccion(String docType, String docNum) throws InterruptedException {
        alliesProteccionDefinition.getPensionerDataFromProteccionUsingFalcon(docType,docNum);
    }


    @When("^consulto los datos de un pensionado inexistente$")
    public void consultoLosDatosDeUnPensionadoInexistente() {
        alliesProteccionDefinition.getRandomIDPensionerDataFromProteccionUsingFalcon();
    }


    @When("^informo a proteccion un descuento por pago de prestamo de un cliente inexistente$")
    public void informoAProteccionUnDescuentoPorPagoDePrestamoDeUnClienteInexistente() {
        alliesProteccionDefinition.informNonExistentPensionerDiscount();
    }

    @Then("^obtengo los datos obtenidos corresponden a los que tiene la AFP$")
    public void obtengoLosDatosObtenidosCorrespondenALosQueTieneLaAFP() {
        alliesProteccionDefinition.comparePensionersFromProteccionAndFalcon();
    }

    @Then("^obtengo que la persona no existe en proteccion$")
    public void obtengoQueLaPersonaNoExisteEnProteccion() {
        alliesProteccionDefinition.verifyNonExistentPerson();
    }

    @And("^el cliente no registra descuentos$")
    public void elClienteNoRegistraDescuentos() {
        alliesProteccionDefinition.verifyPensionersWithoutDiscounts();
    }

    @And("^el cliente tiene el tipo de pension esperada \"([^\"]*)\"$")
    public void elClienteTieneElTipoDePensionEsperada(String pensionType) {
        alliesProteccionDefinition.verifyPensionerType(pensionType);
    }

    @When("^informo a proteccion un descuento por pago de prestamo a partir del mes actual \"([^\"]*)\" \"([^\"]*)\"$")
    public void informoAProteccionUnDescuentoPorPagoDePrestamoAPartirDelMesActual(String docType, String docNum) {
        alliesProteccionDefinition.informNewPensionerDiscount(docType, docNum);
    }

    @Then("^el descuento informado es visible en los descuentos del pensionado \"([^\"]*)\" \"([^\"]*)\"$")
    public void elDescuentoInformadoEsVisibleEnLosDescuentosDelPensionado(String docType, String docNum) throws InterruptedException {
        alliesProteccionDefinition.getPensionerDataFromProteccionUsingFalcon(docType,docNum);
        alliesProteccionDefinition.verifyDiscountAddedToPensioner();
    }

    @When("^informo a proteccion un descuento por pago de prestamo superior a la mesada \"([^\"]*)\" \"([^\"]*)\"$")
    public void informoAProteccionUnDescuentoPorPagoDePrestamoSuperiorALaMesada(String docType, String docNum) {
        alliesProteccionDefinition.informDiscountHigherThanPension(docType, docNum);
    }

    @Then("^obtengo que el descuento no se puede realizar$")
    public void obtengoQueElDescuentoNoSePuedeRealizar() {
        alliesProteccionDefinition.verifyDiscountHigherThanAllowance();
    }

    @Then("^obtengo un consumo exitoso del servicio de consulta de pensionado proteccion$")
    public void obtengoUnConsumoExitosoDelServicioDeConsultaDePensionadoProteccion() {
        alliesProteccionDefinition.verifyResponseStatus(200);
    }

    @Then("^obtengo un consumo exitoso del servicio de informe de descuento a pensionado proteccion$")
    public void obtengoUnConsumoExitosoDelServicioDeInformeDeDescuentoAPensionadoProteccion() {
        alliesProteccionDefinition.verifyResponseStatus(201);
    }




    @Given("^que tengo un cliente potencial beneficiario en proteccion \"([^\"]*)\" \"([^\"]*)\"$")
    public void queTengoUnClientePotencialBeneficiarioEnProteccion(String tipoIDBeneficiario,String numIDBeneficiario) throws Throwable {
        alliesProteccionDefinition.getPensionerDataFromProteccion(tipoIDBeneficiario,numIDBeneficiario);
    }

    @When("^informo a proteccion un descuento por pago de prestamo a partir del mes actualpara el beneficiario \"([^\"]*)\" \"([^\"]*)\"\"([^\"]*)\" \"([^\"]*)\"$")
    public void informoAProteccionUnDescuentoPorPagoDePrestamoAPartirDelMesActualparaElBeneficiario(String tipoIDPensionado,String numIDPensionado,String tipoIDBeneficiario,String numIDBeneficiario) throws Throwable {
        alliesProteccionDefinition.informNewBeneficiariDiscount(tipoIDPensionado,numIDPensionado,tipoIDBeneficiario,numIDBeneficiario);
    }

    @Then("^el descuento informado es visible en los descuentos del beneficiario \"([^\"]*)\" \"([^\"]*)\"$")
    public void elDescuentoInformadoEsVisibleEnLosDescuentosDelBeneficiario(String tipoIDBeneficiario,String numIDBeneficiario) throws Throwable {
        alliesProteccionDefinition.getPensionerDataFromProteccionUsingFalcon(tipoIDBeneficiario,numIDBeneficiario);
        alliesProteccionDefinition.verifyDiscountAddedToPensioner();

    }
}
