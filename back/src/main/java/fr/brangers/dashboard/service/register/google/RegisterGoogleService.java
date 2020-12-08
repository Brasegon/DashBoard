package fr.brangers.dashboard.service.register.google;

import fr.brangers.dashboard.controller.register.SerializeRegister;
import fr.brangers.dashboard.controller.register.google.SRegisterGoogle;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.register.RegisterUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterGoogleService extends Service {
    private SRegisterGoogle person;
    public String token;

    public RegisterGoogleService(SRegisterGoogle person) {
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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(email, OAuth2) VALUES(?, ?)");
            preparedStatement.setString(1, person.getEmail());
            preparedStatement.setString(2, person.getoAuth());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isUserExist() {
        //callYahooWeatherApi();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users WHERE email = ?");
            preparedStatement.setString(1, person.getEmail());
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

    public RegisterUser getRegisterUser() {
        return new RegisterUser();
    }
}
