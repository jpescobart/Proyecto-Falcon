package co.avista.steps;

import co.avista.definitions.ExampleDefinition;

import co.avista.utils.AssertionsManager;
import co.avista.utils.ExecutionInformation;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.io.IOException;

public class ExampleStep {
    private final ExampleDefinition exampleDefinition = new ExampleDefinition();
    @Before
    public void setUp(Scenario scenario) throws IOException {
        ExecutionInformation.setExecutionInformation(scenario);
        AssertionsManager.initAsserts();
    }

    @After
    public void tearDown() {
        AssertionsManager.assertAll();
    }

    @Dado("^que deseo consultar el servicio get de prueba$")
    public void queDeseoConsultarElServicioGetDePrueba() {
        exampleDefinition.buildExampleServiceRequest();
    }

    @Cuando("^realizo una consulta$")
    public void realizoUnaConsulta() {
        exampleDefinition.consumeExampleService();
    }

    @Entonces("^obtengo una respuesta de consulta exitosa$")
    public void obtengoUnaRespuestaDeConsultaExitosa() {
        exampleDefinition.verifyResponse();
    }

    @Entonces("^obtengo una respuesta de consulta fallido$")
    public void obtengoUnaRespuestaDeConsultaFallido() {
        exampleDefinition.failResponse();
    }

}
