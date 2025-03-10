package group3.group3_assignment.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import group3.group3_assignment.controller.AuthController;
import group3.group3_assignment.entity.User;
import group3.group3_assignment.exception.UserNotFoundException;
import group3.group3_assignment.repository.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtUtilServiceImpl implements JwtUtillService {
    UserRepo userRepo;

    // secret key. need to store this somewhere else!
    @Value("${SECRET_KEY}")
    private String secretKey;

    private SecretKey key;

    public JwtUtilServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // creates a crytographic secret key
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public Map<String, String> generateToken(String username) {
        logger.debug("entered generate Token service");
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24); // 1 day
        Date currentDate = new Date(System.currentTimeMillis());
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("user with username: " + username + " is not found."));

        String jws = Jwts.builder()
                .subject(username)
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .expiration(expirationDate)
                .issuedAt(currentDate)
                .signWith(key)
                .compact(); // generate the final compact JWT
        logger.debug("finish building token");

        Map<String, String> response = new HashMap<>(); // create a Json
        logger.debug("created hash map");

        response.put("token", jws);
        logger.debug("added token in json");

        response.put("userId", user.getId().toString());
        logger.debug("added userId in json");

        response.put("username", username);
        logger.debug("added username in json");

        response.put("email", user.getEmail());
        logger.debug("added email in json");

        logger.debug("returning to controller with response " + response);

        return response;
    }

    public Map<String, String> validateToken(String authorizationHeader) {

        // remove the "bearer that is sent from authroization header
        String token = authorizationHeader.replace("Bearer ", "");
        // verify that the token with the secret key
        Jws<Claims> claimsJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
        // get the subject: username
        String username = claimsJws.getPayload().getSubject();
        // get teh user id from the token
        Long userId = claimsJws.getPayload().get("userId", Long.class);
        // get the email from the token
        String email = claimsJws.getPayload().get("email", String.class);
        Map<String, String> response = new HashMap<>();
        // return the username so it can be displayed in front end ("welcome username")
        response.put("userId", userId.toString());
        response.put("username", username);
        response.put("email", email);

        return response;
    }

    // public Claims extractclaims(String authorizationHeader) {
    // String token = authorizationHeader.replace("Bearer ", "");
    // Jws<Claims> claimsJws =
    // Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
    // return claimsJws;
    // }

}
