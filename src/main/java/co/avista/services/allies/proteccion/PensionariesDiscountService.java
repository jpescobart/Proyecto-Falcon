package co.avista.services.allies.proteccion;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;

import java.io.IOException;

public class PensionariesDiscountService extends ServiceTemplate {

    public PensionariesDiscountService(String request) throws IOException {
        super();
        this.request=request;
        uri="https://devapi.avistapp.com/allies/api/pensionaries";
        addHeader("Content-Type","application/json");
        serviceType= ServiceClient.Service_Type.REST_POST;
    }

    @Override
    public void buildService() {
        client.buildRequest(uri,request,headers);
    }
}
