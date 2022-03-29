package co.avista.services.falcon;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;
import java.io.IOException;

public class ServiceDesembolsoMasivoRemanente  extends ServiceTemplate{

    public ServiceDesembolsoMasivoRemanente(String request) throws IOException {
        super();
        this.request = request;
        uri = "https://qaapi.avistapp.com/falcon/api/massiveDisbursement";
        addHeader("Content-Type", "application/json");
        serviceType = ServiceClient.Service_Type.REST_POST;
    }

    @Override
    public void buildService() {
        client.buildRequest(uri, request, headers);
    }
}




