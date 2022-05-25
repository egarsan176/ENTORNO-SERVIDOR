package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
/**
 * Esta clase sirve para generar y validar el token.
 * El token se genera a partir del email del usuario y de su rol
 * @author estefgar
 *
 */
@Component
public class JWTUtil {
	
	public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    @Value("${jwt_secret}")
    private String secret;

    /**
     * MÉTODO que genera el token a partir del email y del rol del usuario
     * @param email
     * @param rol
     * @return
     * @throws IllegalArgumentException
     * @throws JWTCreationException
     */
    public String generateToken(String email, String role, String username) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("email", email)
                .withClaim("role", role)
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                //para fijar la duración del token
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
                .sign(Algorithm.HMAC256(secret));
    }

    /**
     * MÉTODO que sirve para validar el token
     * @param token
     * @return
     * @throws JWTVerificationException
     */
    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("YOUR APPLICATION/PROJECT/COMPANY NAME")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

}