package co.avista.models.services.allies.proteccion.consultapensionados;

import co.avista.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ProteccionConsultaResponse {
    private Data data;

    public static ProteccionConsultaResponse generateProteccionConsultaResponseFromJson(String json) throws JsonProcessingException {
        return JsonUtils.getObjectMapper().readValue(json, ProteccionConsultaResponse.class);
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
