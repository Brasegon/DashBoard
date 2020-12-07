package fr.brangers.service.register;

import fr.brangers.controller.register.SerializeRegister;
import fr.brangers.service.Service;

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
    public String launch() {
        if (!isUserExist()) {
            createUser();
            return("Created user");
        }
        return ("User exist !");
    }

    private void createUser() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO test(login, email, password) VALUES(?, ?, ?)");
            preparedStatement.setString(1, person.getLogin());
            preparedStatement.setString(2, person.getEmail());
            preparedStatement.setString(3, person.getPassword());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean isUserExist() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from users WHERE login = ?");
            preparedStatement.setString(1, person.getLogin());
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
