package co.avista.services.origination;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;

import java.io.IOException;

public class ClientAccountsService extends ServiceTemplate {

    public ClientAccountsService(String clientDocType, String clientDocNum) throws IOException {
        super();
        uri=String.format(uri.concat("/falcon/api/accounts/%s/%s"),clientDocType,clientDocNum);
        serviceType= ServiceClient.Service_Type.REST_GET;

    }

    @Override
    public void buildService() {
        client.buildRequest(uri);
    }
}
