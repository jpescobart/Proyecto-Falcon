package co.avista.services.mambu;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;

import java.io.IOException;

public class ServicesCalendarAccount extends ServiceTemplate {

    public ServicesCalendarAccount(String idNumAccountclient, String cadenaCodificada) throws IOException {
        super();
        uri = String.format("https://avista.sandbox.mambu.com/api/loans/8a81871378ea64e10178ebe68b610416/schedule");
        authorization("Authorization","Basic " + cadenaCodificada);
        addHeader("Accept", "application/vnd.mambu.v2+json");
        serviceType = ServiceClient.Service_Type.REST_GET;
    }




    @Override
    public void buildService() {
        client.buildRequest(uri,headers);
    }
}


