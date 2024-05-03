package matheus.github.task.security.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import matheus.github.task.exception.exceptions.InvalidAuthenticationHeaderException;
import matheus.github.task.jwt.JwtService;
import matheus.github.task.security.constants.PathConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Component
public class ValidateJwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
	   String token = extractTokenFromRequest(request);
	   DecodedJWT decodedJWT = jwtService.getDecodedToken(token);

	   String authoritiesClaim = decodedJWT.getClaim(JwtService.JWT_AUTHORITIES_CLAIM).asString();
	   String username = decodedJWT.getClaim(JwtService.JWT_USERNAME_CLAIM).asString();

	   Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesClaim);

	   UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
			 username,
			 null,
			 authorities);

	   SecurityContextHolder.getContext().setAuthentication(authentication);
	   filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
	   String loginPath = PathConstants.LOGIN_URI_PATH;
	   String registerPath = PathConstants.REGISTER_URI_PATH;
	   String currentRequestPath = request.getRequestURI();
	   List<String> paths = List.of(loginPath, registerPath);
	   return paths.contains(currentRequestPath);
    }

    private String extractTokenFromRequest(HttpServletRequest request) throws InvalidAuthenticationHeaderException {
	   String authorization = request.getHeader("Authorization");

	   if (authorization == null) {
		  throw new InvalidAuthenticationHeaderException("The authentication header is missing");
	   }

	   if (!authorization.split(" ")[0].equals("Bearer")) {
		  throw new InvalidAuthenticationHeaderException("The authentication header is in a invalid format");
	   }

	   return authorization.split(" ")[1];
    }
}
