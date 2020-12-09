package fr.brangers.dashboard.controller;

import fr.brangers.dashboard.controller.login.SLogin;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.login.LoginService;
import fr.brangers.utils.JwtToken;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@ResponseBody
public class test {
    @PostMapping(value = "/api/test", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> loginTest(@RequestHeader(value = "Authorization", defaultValue = "") String token){
        if (JwtToken.verifyToken(token)) {
            return ResponseEntity.status(200).body("Hey");
        }
        else {
            return ResponseEntity.status(400).body("Unauthorized");
        }
    }
}
