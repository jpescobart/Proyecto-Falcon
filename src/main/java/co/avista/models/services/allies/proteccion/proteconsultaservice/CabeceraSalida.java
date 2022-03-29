package co.avista.models.services.allies.proteccion.proteconsultaservice;

public class CabeceraSalida {
    private String mensajeUsuario;

    private String codigo;

    private String tipoError;

    private String mensajeTecnico;

    private String fechaGeneracion;

    private String idIntegracion;

    public String getMensajeUsuario ()
    {
        return mensajeUsuario;
    }

    public void setMensajeUsuario (String mensajeUsuario)
    {
        this.mensajeUsuario = mensajeUsuario;
    }

    public String getCodigo ()
    {
        return codigo;
    }

    public void setCodigo (String codigo)
    {
        this.codigo = codigo;
    }

    public String getTipoError ()
    {
        return tipoError;
    }

    public void setTipoError (String tipoError)
    {
        this.tipoError = tipoError;
    }

    public String getMensajeTecnico ()
    {
        return mensajeTecnico;
    }

    public void setMensajeTecnico (String mensajeTecnico)
    {
        this.mensajeTecnico = mensajeTecnico;
    }

    public String getFechaGeneracion ()
    {
        return fechaGeneracion;
    }

    public void setFechaGeneracion (String fechaGeneracion)
    {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getIdIntegracion ()
    {
        return idIntegracion;
    }

    public void setIdIntegracion (String idIntegracion)
    {
        this.idIntegracion = idIntegracion;
    }

}
