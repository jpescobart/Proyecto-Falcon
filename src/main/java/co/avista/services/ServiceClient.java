package co.avista.services;

import co.avista.exceptions.ServiceException;
import co.avista.logs.Log;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.internal.http.HTTPBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ServiceClient {

    public enum Service_Type {
        REST_GET,
        REST_POST,
        REST_PUT,
        REST_DELETE;
    }

    private RequestSpecification requestSpecification=null;
    private ValidatableResponse response=null;
    private String msj="Request:\n";

    public void buildRequest(String uri){
        requestSpecification=RestAssured.given().baseUri(uri);
        logRequestURI(uri);
    }

    public void buildRequest(String uri, String body){
        buildRequest(uri);
        requestSpecification=requestSpecification.body(body);
        logRequestBody(body);
    }

    public void buildRequest(String uri, String[] basicAuth){
        buildRequest(uri);
        requestSpecification=requestSpecification.auth().basic(basicAuth[0],basicAuth[1]);
        logUserAndPassword(basicAuth[0],basicAuth[1]);
    }

    public void buildRequest(String uri, String body, String[] basicAuth){
        buildRequest(uri,basicAuth);
        requestSpecification=requestSpecification.body(body);
        logRequestBody(body);
    }

    public void buildRequest(String uri, Map<String,?> headers){
        buildRequest(uri);
        requestSpecification=requestSpecification.headers(headers);
        logRequestHeaders(headers);
    }

    public void buildRequest(String uri, String body, Map<String,?> headers){
        buildRequest(uri,body);
        requestSpecification=requestSpecification.headers(headers);
        logRequestHeaders(headers);
    }

    public void buildRequest(String uri, String body,  String[] basicAuth, Map<String,?> headers){
        buildRequest(uri,body,basicAuth);
        requestSpecification=requestSpecification.headers(headers);
        logRequestHeaders(headers);
    }

    public void buildRequest(String uri, Map<String,?> headers, Map<String,?> parameters){
        buildRequest(uri,headers);
        requestSpecification=requestSpecification.params(parameters);
        logRequestParameters(headers);
    }

    public void consumeService(Service_Type type) throws ServiceException {
        Response localResponse;
        switch (type){
            case REST_POST:
                localResponse = requestSpecification.when().post();
                break;
            case REST_GET:
                localResponse=requestSpecification.when().get();
                break;
            case REST_PUT:
                localResponse=requestSpecification.when().put();
                break;
            case REST_DELETE:
                localResponse=requestSpecification.when().delete();
                break;
            default:
                throw new ServiceException("Tipo de servicio sin definir");

        }

        response=localResponse.then().log().all();

    }

    public String getResponseAsString(){
        return response.extract().body().asString();
    }

    public int getStatus(){
        return response.extract().statusCode();
    }

    private void logRequestURI(String uri){
        msj=msj.concat(String.format("URI: %s",uri));
        Log.LOGGER.info(msj);
    }

    private void logRequestBody(String body){
        msj=String.format("Body: %s",body);
        Log.LOGGER.info(msj);
    }

    private void logRequestHeaders(Map<String,?> headers){
        msj=String.format("Headers: %s",headers.toString());
        Log.LOGGER.info(msj);
    }

    private void logRequestParameters(Map<String,?> params){
        msj=String.format("Parameters: %s",params.toString());
        Log.LOGGER.info(msj);
    }


    private void logUserAndPassword(String user, String password){
        msj=String.format("User: %s %n Password: %s %n",user, password);
        Log.LOGGER.info(msj);
    }

}