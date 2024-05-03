package matheus.github.task.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.jwt.JwtService;
import matheus.github.task.security.constants.PathConstants;
import matheus.github.task.security.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class GenerateJwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	   Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   try {
		  String token = jwtService.getToken(authentication.getPrincipal().toString());
		  response.setHeader(SecurityConstants.AUTHENTICATION_HEADER, token);
	   } catch (UserNotFoundException e) {
		  throw new RuntimeException(e);
	   }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	   String loginPath = PathConstants.LOGIN_URI_PATH;
	   String currentRequestPath = request.getRequestURI();
	   return !currentRequestPath.equals(loginPath);
    }

}
