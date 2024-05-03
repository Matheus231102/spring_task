package matheus.github.task.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import matheus.github.task.entities.UserEntity;
import matheus.github.task.exception.exceptions.UserNotFoundException;
import matheus.github.task.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_AUTHENTICATION;

    @Value("${jwt.issuer}")
    private String JWT_ISSUER;

    public static final String JWT_USERNAME_CLAIM = "username";
    public static final String JWT_AUTHORITIES_CLAIM = "authorities";
    private final int HOURS_TO_INCREASE = 8;


    @Autowired
    private UserRepository userRepository;

    public String getToken(String username) throws UserNotFoundException {
	   Optional<UserEntity> user = userRepository.findByUsername(username);
	   if (user.isPresent()) {
		  return generateToken(user.get());
	   }
	   throw new UserNotFoundException();
    }

    public String generateToken(UserEntity user) {
	   try {
		  return JWT.create()
				.withIssuer(JWT_ISSUER)
				.withClaim(JWT_USERNAME_CLAIM, user.getUsername())
				.withClaim(JWT_AUTHORITIES_CLAIM, authoritiesToStringSeparetedComma(user.getAuthorities()) )
				.withExpiresAt(generateExpirationDate(HOURS_TO_INCREASE))
				.sign(getAlgorithm());

	   } catch (IllegalArgumentException e) {
		  throw new RuntimeException(e);
	   } catch (JWTCreationException e) {
		  throw new RuntimeException("Error generating token: " + e.getMessage());
	   }
    }

    private String authoritiesToStringSeparetedComma(Collection<? extends GrantedAuthority> authorities) {
	   return authorities.stream()
			 .map(grantedAuthority -> grantedAuthority.toString())
			 .collect(Collectors.joining(", "));
    }

    private Instant generateExpirationDate(int timeInHours) {
	   return increaseExpirationHour(timeInHours).toInstant(ZoneOffset.of("-03:00"));
    }

    private LocalDateTime increaseExpirationHour(int timeInHours) {
	   return LocalDateTime.now().plusHours(timeInHours);
    }

    public DecodedJWT getDecodedToken(String token) {
	   try {
		  return JWT.require(getAlgorithm())
				.withIssuer(JWT_ISSUER)
				.build()
				.verify(token);
	   } catch (JWTVerificationException e) {
		  throw new RuntimeException(e);
	   }
    }

    private Algorithm getAlgorithm(){
	   try {
		  return Algorithm.HMAC256(SECRET_AUTHENTICATION);
	   } catch (IllegalArgumentException e) {
		  throw new RuntimeException("Can not define the algorithm");
	   }
    }

}
