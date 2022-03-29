package co.avista.models.services.allies.proteccion.informedescuentos;

import co.avista.utils.GeneralUtilities;

public class Auditing {
    private String ipAddress;

    private String user;

    private String transactionId;

    public static Auditing generateWithDefaultData(){
        Auditing auditing = new Auditing();
        auditing.setIpAddress("10.0.0.0");
        auditing.setTransactionId(GeneralUtilities.uuidGenerator());
        auditing.setUser("Avista");
        return auditing;
    }

    public String getIpAddress ()
    {
        return ipAddress;
    }

    public void setIpAddress (String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public String getUser ()
    {
        return user;
    }

    public void setUser (String user)
    {
        this.user = user;
    }

    public String getTransactionId ()
    {
        return transactionId;
    }

    public void setTransactionId (String transactionId)
    {
        this.transactionId = transactionId;
    }
}
