package matheus.github.task.controllers;

import matheus.github.task.security.constants.PathConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PathConstants.LOGIN_URI_PATH)
public class LoginController {

    @PostMapping
    private void loginUser() {}

}
