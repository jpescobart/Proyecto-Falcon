package co.avista.services.CouchBase;


import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;

import java.io.IOException;

public class ServiceCouchBase extends ServiceTemplate{


    public ServiceCouchBase(String request,String token) throws IOException {
        super();
        this.request = request;
        uri = "https://couchbase-proxy.avistapp.com/users/executequery";
        //authorization("TYPE","Bearer Token");
        //authorization("Token",token);
        authorization("Authorization","Bearer"+" "+token);
        addHeader("Content-Type", "application/json");
        addHeader("Accept","*/*");
        serviceType = ServiceClient.Service_Type.REST_POST;
    }


    @Override
    public void buildService() {
        client.buildRequest(uri, request, headers);


    }

}





