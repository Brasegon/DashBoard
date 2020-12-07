package fr.brangers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerMain {
    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
        SqlConnector.createConnection("jdbc:mysql://localhost:3306/dashboard?serverTimezone=UTC", "root", "");
    }
}
