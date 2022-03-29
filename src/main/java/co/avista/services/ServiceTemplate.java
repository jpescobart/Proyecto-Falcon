package co.avista.services;

import co.avista.exceptions.ServiceException;
import co.avista.logs.Log;
import co.avista.utils.PropertiesManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class ServiceTemplate {

    protected final ServiceClient client = new ServiceClient();
    protected String uri;
    protected String request="";
    protected ServiceClient.Service_Type serviceType;
    protected Map<String,String> headers=new HashMap();
    protected Map<String,String> params=new HashMap();
    protected String[] basicAuth= new String[2];
    protected String response;
    protected int status;

    public ServiceTemplate() throws IOException {
        uri=PropertiesManager.getProperty(PropertiesManager.PropertyFiles.CONFIGURATION,"QA.ENV");
    }

    public void addHeader(String name, String value){
        headers.put(name,value);
    }

    public void authorization(String name, String value){
        headers.put(name,value);
    }

    public void addParam(String name, String value){
        params.put(name,value);
    }

    public void addBearerToken(String token){
        addHeader("Authorization","Bearer ".concat(token));
    }

    public void addBasicAuth(String user, String password){
        basicAuth[0]=user;
        basicAuth[1]=password;
    }


    public abstract void buildService();

    public void consumeService() throws ServiceException {
        client.consumeService(serviceType);
        response=client.getResponseAsString();
        status=client.getStatus();
        logResponse();
    }

    public int getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    private void logResponse(){
        String msj=String.format("%nResponse: %nBody:%s",response);
        Log.LOGGER.info(msj);
        msj=String.format("Status:%s %n",status);
        Log.LOGGER.info(msj);
    }
}
