package fr.brangers.dashboard.service.login.google;

import fr.brangers.dashboard.controller.login.SLogin;
import fr.brangers.dashboard.controller.login.google.SLoginGoogle;
import fr.brangers.dashboard.controller.register.google.SRegisterGoogle;
import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.login.DataLogin;
import fr.brangers.dashboard.service.register.RegisterUser;
import fr.brangers.utils.JwtToken;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginGoogleService extends Service {
    private SLoginGoogle person;
    public String token;
    private DataLogin dataLogin;

    public LoginGoogleService(SLoginGoogle person) {
        this.person = person;
    }

    @Override
    public IResponse launch() {
        if (isUserExist()) {
            return(new DataResponse("Login Successful", "success", dataLogin));
        }
        return (new Response("Invalid users", "error"));
    }


    private boolean isUserExist() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users WHERE email = ? AND OAuth2 = 'GOOGLE'");
            preparedStatement.setString(1, person.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            if (rs.getRow() != 0) {
                if (googleAuth()) {
                    dataLogin = new DataLogin(person.getToken(), person.getEmail());
                    return true;
                }
                return false;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    private boolean googleAuth() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/tokeninfo?id_token=" + person.getToken())
                .queryParam("format", "json");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            HttpEntity<String> response = restTemplate.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    String.class);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    public RegisterUser getRegisterUser() {
        return new RegisterUser();
    }
}
