package fr.brangers.dashboard.controller.auth;

import fr.brangers.dashboard.controller.login.SLogin;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.login.LoginService;
import fr.brangers.utils.JwtToken;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@ResponseBody
public class Auth {

    @PostMapping(value = "/api/authentification", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> authentification(@RequestBody SAuth auth){
        if (auth.getMethod().equalsIgnoreCase("legacy")) {
            if (JwtToken.verifyToken(auth.getToken())) {
                return ResponseEntity.status(200).body(new Response("OK", "success"));
            }
        } else if (auth.getMethod().equalsIgnoreCase("google")) {
            if (JwtToken.verifyTokenGoogle(auth.getToken())) {
                return ResponseEntity.status(200).body(new Response("OK", "success"));
            }
        }
        return ResponseEntity.status(400).body(new Response("unauthorized", "error"));
    }
}
