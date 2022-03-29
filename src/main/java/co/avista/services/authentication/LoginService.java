package co.avista.services.authentication;

import co.avista.models.services.authentication.login.LoginRequest;
import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;
import co.avista.utils.JsonUtils;

import java.io.IOException;

public class LoginService extends ServiceTemplate {

    public LoginService(LoginRequest loginRequest) throws IOException {
        super();
        uri=uri.concat("/authentication/login");
        request= JsonUtils.jsonSerializer(loginRequest);
        serviceType= ServiceClient.Service_Type.REST_POST;
    }

    @Override
    public void buildService() {
        client.buildRequest(uri,request);
    }
}
