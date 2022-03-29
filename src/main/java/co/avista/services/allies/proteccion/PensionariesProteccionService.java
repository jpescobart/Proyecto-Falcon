package co.avista.services.allies.proteccion;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;

import java.io.IOException;

public class PensionariesProteccionService extends ServiceTemplate {

    public PensionariesProteccionService(String docType, String docNum) throws IOException {
        super();
        uri=String.format("https://devapi.avistapp.com/allies/api/pensionaries/%s-%s",docType,docNum);
        addHeader("Accept","*/*");
        addParam("ipAddress","10.0.0.0");
        addParam("transactionId","2");
        addParam("user","AVISTA");
        serviceType= ServiceClient.Service_Type.REST_GET;
    }

    @Override
    public void buildService() {
        client.buildRequest(uri,headers,params);
    }

}
