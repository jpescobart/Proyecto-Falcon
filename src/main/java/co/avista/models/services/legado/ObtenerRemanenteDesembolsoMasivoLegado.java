package co.avista.models.services.legado;

public class ObtenerRemanenteDesembolsoMasivoLegado {

    private String  documentoIdentidad;
    private String  documentoTransaccionId ;
    private String  conceptoCuenta;
    private String  numeroCuenta ;
    private String  valorMonto;
    private int  documentoIdentidadNumerico ;

    public String getValorMonto() {
        return valorMonto;
    }

    public void setValorMonto(String valorMonto) {
        this.valorMonto = valorMonto;
    }

    public int getDocumentoIdentidadNumerico() {
        return Integer.parseInt(getDocumentoIdentidad()) ;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getDocumentoTransaccionId() {
        return documentoTransaccionId;
    }

    public void setDocumentoTransaccionId(String documentoTransaccionId) {
        this.documentoTransaccionId = documentoTransaccionId;
    }

    public String getConceptoCuenta() {
        return conceptoCuenta;
    }

    public void setConceptoCuenta(String conceptoCuenta) {
        this.conceptoCuenta = conceptoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public String toString() {
        return
                  documentoIdentidad + '\'' +
                  documentoTransaccionId + '\'' +
                  conceptoCuenta + '\'' +
                  numeroCuenta + '\'' +
                  valorMonto + '\'';
    }
}
