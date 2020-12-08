package fr.brangers.dashboard.service.login;

import fr.brangers.dashboard.service.Data;

public class DataLogin extends Data {
    private String token;
    private String email;

    public DataLogin(String token, String email) {
        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }
}
