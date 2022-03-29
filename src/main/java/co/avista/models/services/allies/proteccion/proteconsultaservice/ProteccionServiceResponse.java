package co.avista.models.services.allies.proteccion.proteconsultaservice;

import co.avista.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ProteccionServiceResponse {
    private CabeceraSalida cabeceraSalida;

    private DatosSalida datosSalida;

    public static ProteccionServiceResponse generateProteccionServiceModelFromJson(String json) throws JsonProcessingException {
        return JsonUtils.getObjectMapper().readValue(json, ProteccionServiceResponse.class);
    }

    public CabeceraSalida getCabeceraSalida ()
    {
        return cabeceraSalida;
    }

    public void setCabeceraSalida (CabeceraSalida cabeceraSalida)
    {
        this.cabeceraSalida = cabeceraSalida;
    }

    public DatosSalida getDatosSalida ()
    {
        return datosSalida;
    }

    public void setDatosSalida (DatosSalida datosSalida)
    {
        this.datosSalida = datosSalida;
    }

}
