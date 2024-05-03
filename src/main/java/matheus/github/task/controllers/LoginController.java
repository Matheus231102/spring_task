package matheus.github.task.controllers;

import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.services.implementation.login.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = PathConstants.LOGIN_URI_PATH)
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @PostMapping
    private void loginUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());
    }

}
