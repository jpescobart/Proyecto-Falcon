package co.avista.models.services.authentication.common;

import co.avista.models.services.authentication.common.Data;
import co.avista.models.services.authentication.common.Error;

public class ErrorResponse {
    private String environment;
    private boolean debug;
    private String status;
    private Data data;
    private Error error;

    public ErrorResponse() {
    }

    public ErrorResponse(boolean debug, String status, Data data, Error error) {
        this.debug = debug;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public ErrorResponse(String environment, String status, Data data, Error error) {
        this.environment = environment;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public ErrorResponse(String environment,boolean debug, String status, Data data, Error error) {
        this.environment = environment;
        this.debug = debug;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public boolean getDebug() {
        return debug;
    }

    public String getStatus() {
        return status;
    }

    public Data getData() {
        return data;
    }

    public Error getError() {
        return error;
    }

    public String getEnvironment() {
        return environment;
    }
}