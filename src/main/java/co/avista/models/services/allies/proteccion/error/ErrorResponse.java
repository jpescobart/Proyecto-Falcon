package co.avista.models.services.allies.proteccion.error;

import co.avista.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ErrorResponse {
    private Error error;

    public static ErrorResponse generateErrorResponseFromJson(String json) throws JsonProcessingException {
        return JsonUtils.getObjectMapper().readValue(json, ErrorResponse.class);
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
