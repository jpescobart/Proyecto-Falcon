package co.avista.services.falcon;
import co.avista.services.ServiceClient;
import co.avista.services.ServiceTemplate;
import java.io.IOException;

public class ServiceOriginationFalcon extends ServiceTemplate {

    public ServiceOriginationFalcon(String request) throws IOException {
        super();
        this.request = request;
        uri = "https://qaapi.avistapp.com/falcon/api/accounts";
        authorization("TYPE","Bearer Token");
        authorization("Token","eyJhbGciOiJSUzI1NiIsImtpZCI6Ijk4QkFBQThDNjZFQTY5NTkiLCJrdHkiOiJSU0EifQ.eyJQYXJ0bmVySWQiOiI5MTIyMDUzRi05OThBLTQ3M0ItODRGNS01MDM2QzE4NEE1RkEiLCJleHAiOjE2MzQzMDA5OTAsImlhdCI6MTYwMjc2NDk5MCwianRpIjoiYjJiOGJmN2MtODQ2ZC00NDk1LWE2OWQtYWU5N2ViNzI0MDA1Iiwicm9sZSI6IlBhcnRuZXIifQ.Kq1dlZteoWwoR1aa0ccTK4-0XhsO96IUJLO3TGcEoSU-h1ufTi4jPChCIgC4R1WlfkRcY7KxOYEqy_iqganii1_w4zoFft1Jaiy2kJMrhUqv022uEeIhHNuuNbkcp3voHrVd467BFrxxEpfHt-MUm8b5mBxZG7PEk5Rs5B8V3h16NQS1Z6Kna4WnLCnrf08CMb3aPX_1O3bL-6XHMCsEKGUQYttmb0JVpEAAiv2zUAeDg5uaZY295b3JJj5RUfCOPIVw9OLT6NEUo1X4LBMLg4houZNwri5qyTHaRfAVNtoyOw3WaFmT3V9XSN7kDeXDo3Bw-mjt-tB_B0HPpnoJCg");
        addHeader("Content-Type", "application/json");
        serviceType = ServiceClient.Service_Type.REST_POST;

    }


    @Override
    public void buildService() {
        client.buildRequest(uri, request, headers);
    }
}

