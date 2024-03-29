package fr.brangers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ServerMain extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(ServerMain.class, args);
        SqlConnector.createConnection("jdbc:mysql://localhost:3306/dashboard?serverTimezone=UTC", "root", "");
    }
}
