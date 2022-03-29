package co.avista.services.CouchBase;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;
import co.avista.utils.JsonUtils;

import java.io.IOException;

public class ServiceToken  extends ServiceTemplate {

    public ServiceToken(String request) throws IOException {
        super();
        this.request = request;
        uri = "https://couchbase-proxy.avistapp.com/users/login";
        addHeader("Content-Type", "application/json");
        addHeader("Accept","*/*");
        serviceType = ServiceClient.Service_Type.REST_POST;
    }



    @Override
    public void buildService() {
        client.buildRequest(uri,request,headers);
    }
}
