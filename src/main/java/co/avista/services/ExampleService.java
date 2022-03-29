package co.avista.services;


import java.io.IOException;

public class ExampleService extends ServiceTemplate {

    public ExampleService() throws IOException {
        super();
        uri="https://jsonplaceholder.typicode.com/users";
        serviceType= ServiceClient.Service_Type.REST_GET;
    }

    @Override
    public void buildService() {
        client.buildRequest(uri);
    }


}
