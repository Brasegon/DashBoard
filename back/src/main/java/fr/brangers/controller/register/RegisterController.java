package fr.brangers.controller.register;

import fr.brangers.controller.Response;
import fr.brangers.service.RegisterUser;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    @RequestMapping(value = "/register", method= RequestMethod.POST)
    public Response registerUser() {
        return new Response(new RegisterUser(), "Hey ca marche");
    }
}
