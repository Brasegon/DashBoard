package fr.brangers.dashboard.controller.login.google;

public class SLoginGoogle {
    private String email;
    private String token;

    public SLoginGoogle(String email, String token) {
        this.email = email;
        this.token = token;
    }
    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }
}
