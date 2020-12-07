package fr.brangers.controller.register;

import fr.brangers.controller.Response;
import fr.brangers.service.register.RegisterService;
import fr.brangers.service.register.RegisterUser;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class RegisterController {
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> registerUser(@RequestBody SerializeRegister person) throws InterruptedException {
        RegisterService registerService = new RegisterService(person);
        registerService.launch();
        return ResponseEntity.status(200).body("OK");
    }

}
