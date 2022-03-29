package co.avista.services.mambu;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;
import co.avista.utils.GeneralUtilities;

import java.io.IOException;

public class ServicesConsultClientsMambu extends ServiceTemplate {



    public ServicesConsultClientsMambu(String idAccountHolderKey, String cadenaCodificada) throws IOException {
        super();
        uri = String.format("https://avista.sandbox.mambu.com/api/clients/%s",idAccountHolderKey);
        authorization("Authorization","Basic " + cadenaCodificada);
        addHeader("Accept", "application/vnd.mambu.v2+json");
        serviceType = ServiceClient.Service_Type.REST_GET;
    }


    @Override
    public void buildService() {
        client.buildRequest(uri,headers);
    }
}

