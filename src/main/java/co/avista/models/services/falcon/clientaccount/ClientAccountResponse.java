package co.avista.models.services.falcon.clientaccount;

import co.avista.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ClientAccountResponse {
    private String status;
    private Data data;
    private String error;

    public static ClientAccountResponse generateClientCreditResponseFromJson(String json) throws JsonProcessingException {
        return JsonUtils.getObjectMapper().readValue(json, ClientAccountResponse.class);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



}
