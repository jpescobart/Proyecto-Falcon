package co.avista.definitions;


import co.avista.exceptions.ServiceException;
import co.avista.models.services.authentication.login.LoginRequest;
import co.avista.models.services.authentication.login.SuccessLoginResponse;
import co.avista.services.CouchBase.ServiceToken;
import co.avista.services.authentication.LoginService;
import co.avista.utils.AssertionsManager;
import co.avista.utils.JsonUtils;
import co.avista.utils.PropertiesManager;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class LoginDefinition {
    private LoginService loginService;
    private LoginRequest loginRequestModel;
    private ServiceToken serviceTokenLoguin;
    private SuccessLoginResponse successLoginResponseModel;
    private String uuid;
    private String token;

    private static final String MSJ_ERROR_PARSE_RESPONSE ="se presento un problema con la estructura de la respuesta: %s";
    private static final String MSJ_ERROR_BUILDING_REQUEST ="se presento un problema construyendo el request: %s";
    private static final String MSJ_ERROR_CONSUME ="se presento un problema consumiendo el servicio: %s";

    public void buildValidLoginServiceRequest() {
        try {
            loginRequestModel =new LoginRequest(PropertiesManager.getProperty(PropertiesManager.PropertyFiles.CONFIGURATION,"USER"),
                    PropertiesManager.getProperty(PropertiesManager.PropertyFiles.CONFIGURATION,"PASS"));
            loginService=new LoginService(loginRequestModel);
            loginService.buildService();
        } catch (IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_BUILDING_REQUEST,e.getMessage()));
        }
    }

    public void consumeLoginService(){
        try {
            loginService.consumeService();
        } catch (ServiceException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }

    public void verifySuccessLogin() {
        try {
            AssertionsManager.softAssertEqual(200,loginService.getStatus(),"Status code");
            successLoginResponseModel = JsonUtils.getObjectMapper().readValue(loginService.getResponse(), SuccessLoginResponse.class);
            AssertionsManager.softAssertEqual("dev",successLoginResponseModel.getEnvironment(),"environment value");
            AssertionsManager.softAssertEqual("Success",successLoginResponseModel.getStatus(),"status value");
            AssertionsManager.softAssertEqual("Exito",successLoginResponseModel.getData().getStatus(),"data status value");
            AssertionsManager.softAssertEqual("",successLoginResponseModel.getData().getDescription(),"data description value");
            AssertionsManager.softAssertNotEqual("",successLoginResponseModel.getData().getToken(),"data token value");
            AssertionsManager.softAssertIsNull(successLoginResponseModel.getError(),"error value");
            AssertionsManager.assertAll();
        } catch (JsonProcessingException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_PARSE_RESPONSE,e.getMessage()));
        }
    }

    public void completeSuccessLogin (){
        buildValidLoginServiceRequest();
        consumeLoginService();
        verifySuccessLogin();
        uuid=loginRequestModel.getUuid();
        token=successLoginResponseModel.getData().getToken();
    }

    public void generateExpiredSesion() {
        String firstUuid;
        String firstToken;
        completeSuccessLogin();
        firstUuid=uuid;
        firstToken=token;
        completeSuccessLogin();
        uuid=firstUuid;
        token=firstToken;
    }

}
