package co.avista.models.services.allies.proteccion.proteconsultaservice;

public class DatosSalida {
    private String fechaConsulta;

    private String anoPension;

    private String tipoRiesgo;

    private String fechaGeneracionPension;

    private String valorTotalDescuentos;

    private String valorMesadaPensional;

    private String descripcionDescuentos;

    private Identificacion identificacion;

    private String nombreCompleto;

    private String codigoOperacion;

    public String getFechaConsulta ()
    {
        return fechaConsulta;
    }

    public void setFechaConsulta (String fechaConsulta)
    {
        this.fechaConsulta = fechaConsulta;
    }

    public String getAnoPension ()
    {
        return anoPension;
    }

    public void setAnoPension (String anoPension)
    {
        this.anoPension = anoPension;
    }

    public String getTipoRiesgo ()
    {
        return tipoRiesgo;
    }

    public void setTipoRiesgo (String tipoRiesgo)
    {
        this.tipoRiesgo = tipoRiesgo;
    }

    public String getFechaGeneracionPension ()
    {
        return fechaGeneracionPension;
    }

    public void setFechaGeneracionPension (String fechaGeneracionPension)
    {
        this.fechaGeneracionPension = fechaGeneracionPension;
    }

    public String getValorTotalDescuentos ()
    {
        return valorTotalDescuentos;
    }

    public void setValorTotalDescuentos (String valorTotalDescuentos)
    {
        this.valorTotalDescuentos = valorTotalDescuentos;
    }

    public String getValorMesadaPensional ()
    {
        return valorMesadaPensional;
    }

    public void setValorMesadaPensional (String valorMesadaPensional)
    {
        this.valorMesadaPensional = valorMesadaPensional;
    }

    public String getDescripcionDescuentos ()
    {
        return descripcionDescuentos;
    }

    public void setDescripcionDescuentos (String descripcionDescuentos)
    {
        this.descripcionDescuentos = descripcionDescuentos;
    }

    public Identificacion getIdentificacion ()
    {
        return identificacion;
    }

    public void setIdentificacion (Identificacion identificacion)
    {
        this.identificacion = identificacion;
    }

    public String getNombreCompleto ()
    {
        return nombreCompleto;
    }

    public void setNombreCompleto (String nombreCompleto)
    {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCodigoOperacion ()
    {
        return codigoOperacion;
    }

    public void setCodigoOperacion (String codigoOperacion)
    {
        this.codigoOperacion = codigoOperacion;
    }
}
