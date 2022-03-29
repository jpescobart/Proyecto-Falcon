package co.avista.services.allies.proteccion;

import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;

import java.io.IOException;

public class ProteccionConsultaPensionadoService extends ServiceTemplate {

    public ProteccionConsultaPensionadoService(String docType, String docNum, String token) throws IOException {
        super();
        uri=String.format("https://api-qa.proteccion.com.co/pensionados/datospension/resumen/%s_%s",docType,docNum);
        addHeader("x-api-key", "yYsymqEm3sJXvp6FC7vuhxuPD39BvpnsbGa3WM7K");
        addHeader("Authorization",token);
        addHeader("Accept","application/json; charset=utf-8");

        addParam("idAplicacion","353");
        addParam("direccionIP","10.0.0.0");
        addParam("idTransaccion","2");
        addParam("usuario","AVISTA");
        serviceType= ServiceClient.Service_Type.REST_GET;
    }

    @Override
    public void buildService() {
        client.buildRequest(uri,headers,params);
    }
}
