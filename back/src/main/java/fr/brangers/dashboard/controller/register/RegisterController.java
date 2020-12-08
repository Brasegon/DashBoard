package fr.brangers.dashboard.controller.register;

import fr.brangers.dashboard.controller.register.google.SRegisterGoogle;
import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.service.Service;
import fr.brangers.dashboard.service.register.RegisterService;
import fr.brangers.dashboard.service.register.google.RegisterGoogleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class RegisterController {
    @PostMapping(value = "/api/register", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> registerUser(@RequestBody SerializeRegister person) throws InterruptedException {
        RegisterService registerService = new RegisterService(person);
        IResponse response = registerService.launch();
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping(value = "/api/registerGoogle", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> registerUserGoogle(@RequestBody SRegisterGoogle person) throws InterruptedException {
        Service registerService = new RegisterGoogleService(person);
        IResponse response = registerService.launch();
        return ResponseEntity.status(200).body(response);
    }

}
