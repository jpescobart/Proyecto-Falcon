package co.avista.definitions;

import co.avista.exceptions.ServiceException;
import co.avista.services.ExampleService;
import co.avista.utils.AssertionsManager;
import java.io.IOException;

public class ExampleDefinition {
    private static final String MSJ_ERROR_CONSUME ="se presento un problema consumiendo el servicio: %s";
    private static final String MSJ_ERROR_BUILD ="se presento un problema construyendo el servicio: %s";
    private static final String MSJ_STATUS_CODE ="Status code";

    private ExampleService exampleService;

    public void buildExampleServiceRequest() {
        try{
            exampleService = new ExampleService();
            exampleService.buildService();
        } catch (IOException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_CONSUME,e.getMessage()));
        }
    }

    public void consumeExampleService() {
        try {

            exampleService.consumeService();
        } catch (ServiceException e) {
            AssertionsManager.failTest(String.format(MSJ_ERROR_BUILD,e.getMessage()));
        }
    }

    public void verifyResponse()
    {
        AssertionsManager.softAssertEqual(500,exampleService.getStatus(),MSJ_STATUS_CODE);
    }

    public void failResponse() {
        AssertionsManager.softAssertEqual(200,exampleService.getStatus(),MSJ_STATUS_CODE);
    }
}

//--