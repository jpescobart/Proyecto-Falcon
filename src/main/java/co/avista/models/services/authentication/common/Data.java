package co.avista.models.services.authentication.common;

public class Data {
    private String codeStatus;
    private String status;
    private String description;
    private String token;

    public Data() {
    }

    public Data(String codeStatus, String status, String description, String token) {
        this.codeStatus = codeStatus;
        this.status = status;
        this.description = description;
        this.token = token;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
