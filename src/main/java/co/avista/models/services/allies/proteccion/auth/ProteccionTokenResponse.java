package co.avista.models.services.allies.proteccion.auth;

import co.avista.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ProteccionTokenResponse {
    private String access_token;
    private String expires_in;
    private String token_type;

    public static ProteccionTokenResponse generateProteccionTokenResponseFromJson(String json) throws JsonProcessingException {
        return JsonUtils.getObjectMapper().readValue(json, ProteccionTokenResponse.class);
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
