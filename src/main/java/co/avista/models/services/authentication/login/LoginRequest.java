package co.avista.models.services.authentication.login;

import co.avista.utils.GeneralUtilities;

public class LoginRequest {
    private String username;
    private String password;
    private String uuid;

    public LoginRequest(String username, String password, String uuid) {
        this.username = username;
        this.password = password;
        this.uuid = uuid;
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
        this.uuid= GeneralUtilities.uuidGenerator();
    }

    public LoginRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}

