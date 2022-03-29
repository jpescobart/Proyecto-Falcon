package co.avista.services.allies.proteccion;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;
import java.io.IOException;

public class ProteccionAuthService extends ServiceTemplate {

    public ProteccionAuthService() throws IOException {
        super();
        uri="https://terceros-qa.auth.us-east-1.amazoncognito.com/oauth2/token";
        request="grant_type=client_credentials";
        addBasicAuth("42p9a87nrmflu3mt48k1vfmlv", "1e3ho59d33l0lbsr34º8pj428hgh77vuhkcvauqd6g0v1db9jjgar");
        addHeader("Content-Type", "application/x-www-form-urlencoded");
        addHeader("Authorization","Basic NDJwOWE4N25ybWZsdTNtdDQ4azF2Zm1sdjoxZTNobzU5ZDMzbDBsYnNyMzQ4cGo0MjhoZ2g3N3Z1aGtjdmF1cWQ2ZzB2MWRiOWpqZ2Fy");
        serviceType= ServiceClient.Service_Type.REST_POST;
    }

    @Override
    public void buildService() {
        client.buildRequest(uri,request,basicAuth,headers);
    }
}
