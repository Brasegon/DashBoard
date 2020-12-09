package fr.brangers.dashboard.controller.login;

import fr.brangers.dashboard.controller.login.google.SLoginGoogle;
import fr.brangers.dashboard.controller.register.google.SRegisterGoogle;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.login.LoginService;
import fr.brangers.dashboard.service.login.google.LoginGoogleService;
import fr.brangers.dashboard.service.register.google.RegisterGoogleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseBody
public class LoginController {
    @PostMapping(value = "/api/login", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> loginUser(@RequestBody SLogin person) throws InterruptedException {
        Service loginService = new LoginService(person);
        IResponse response = loginService.launch();
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping(value = "/api/loginGoogle", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> loginUserGoogle(@RequestBody SLoginGoogle person) throws InterruptedException {
        Service loginService = new LoginGoogleService(person);
        IResponse response = loginService.launch();
        return ResponseEntity.status(200).body(response);
    }
}
