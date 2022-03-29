package co.avista.steps;

import co.avista.definitions.LoginDefinition;
import co.avista.utils.AssertionsManager;
import co.avista.utils.ExecutionInformation;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

import java.io.IOException;

public class LoginSteps {
    private final LoginDefinition loginDefinition= new LoginDefinition();

    @Dado("que tengo una sesion activa en el perfilador")
    public void queTengoUnaSesionActivaEnElPerfilador() {
        loginDefinition.completeSuccessLogin();
    }

    @Dado("que tengo una sesion vencida en el perfilador")
    public void queTengoUnaSesionVencidaEnElPerfilador() {
        loginDefinition.generateExpiredSesion();
    }


}
