package fr.brangers.dashboard.controller.register;

import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.service.register.RegisterService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class RegisterController {
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/register", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> registerUser(@RequestBody SerializeRegister person) throws InterruptedException {
        RegisterService registerService = new RegisterService(person);
        IResponse response = registerService.launch();
        return ResponseEntity.status(200).body(response);
    }

}
