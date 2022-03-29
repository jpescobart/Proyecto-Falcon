package co.avista.services.mambu;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;

import java.io.IOException;

public class ServicesAccountClients extends ServiceTemplate {

    public ServicesAccountClients(String idNumAccountclient, String cadenaCodificada) throws IOException {
        super();
        uri = String.format("https://avista.sandbox.mambu.com/api/loans/%s",idNumAccountclient);
        authorization("Authorization","Basic " + cadenaCodificada);
        addHeader("Accept", "application/vnd.mambu.v2+json");
        serviceType = ServiceClient.Service_Type.REST_GET;
    }


    @Override
    public void buildService() {
        client.buildRequest(uri,headers);
    }
}

