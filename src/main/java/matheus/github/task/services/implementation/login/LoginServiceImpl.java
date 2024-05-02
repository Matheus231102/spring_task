package matheus.github.task.services.implementation.login;

import matheus.github.task.dto.AuthDTO;
import matheus.github.task.services.interfaces.LoginServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginServiceInterface {

    @Override
    public String loginUser(AuthDTO authDTO) {
	   return "Testando novamente";
    }

}