package co.avista.models.services.authentication.login;

import co.avista.models.services.authentication.common.Data;

public class SuccessLoginResponse {
    private String environment;
    private String status;
    private Data data;
    private String error;

    public SuccessLoginResponse() {
    }

    public SuccessLoginResponse(String environment, String status, Data data, String error) {
        this.environment = environment;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public String getEnvironment() {
        return environment;
    }

    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}