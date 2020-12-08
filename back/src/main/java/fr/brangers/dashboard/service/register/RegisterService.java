package fr.brangers.dashboard.service.register;

import fr.brangers.dashboard.controller.register.SerializeRegister;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterService extends Service {

    private SerializeRegister person;
    public String token;

    public RegisterService(SerializeRegister person) {
        this.person = person;
    }

    @Override
    public IResponse launch() {
        if (!isUserExist()) {
            createUser();
            return(new Response("Account successly created", "success"));
        }
        return (new Response("User exist", "error"));
    }

    private void createUser() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode(person.getPassword());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(login, email, password) VALUES(?, ?, ?)");
            preparedStatement.setString(1, person.getLogin());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, password);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isUserExist() {
        //callYahooWeatherApi();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users WHERE login = ? OR email = ?");
            preparedStatement.setString(1, person.getLogin());
            preparedStatement.setString(2, person.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            if (rs.getRow() != 0) {
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /*public String callYahooWeatherApi() {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://api.weatherapi.com/v1/current.json?key=b8ea2cb24b234d608e3171510200712&q=Marseille")
                .queryParam("format", "json");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.GET,
                entity,
                String.class);
        JSONObject json = new JSONObject(response.getBody()).getJSONObject("location");
        System.out.println(json);
        return response.getBody();

    }*/

    public RegisterUser getRegisterUser() {
        return new RegisterUser();
    }
}
