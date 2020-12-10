package fr.brangers.dashboard.service.login;

import fr.brangers.dashboard.controller.login.SLogin;
import fr.brangers.dashboard.controller.register.SerializeRegister;
import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.register.RegisterUser;
import fr.brangers.utils.JwtToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService extends Service {
    private SLogin person;
    public String token;
    private DataLogin dataLogin;

    public LoginService(SLogin person) {
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users WHERE email = ? AND OAuth2 = 'default'");
            preparedStatement.setString(1, person.getEmail());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            if (rs.getRow() != 0) {
                if (encoder.matches(person.getPassword(), rs.getString("password"))) {
                    dataLogin = new DataLogin(JwtToken.createToken(person, rs.getInt("id")), person.getEmail());
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


    public RegisterUser getRegisterUser() {
        return new RegisterUser();
    }
}
