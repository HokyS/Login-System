package com.example.LoginSystem.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.LoginSystem.entities.LoginDTO;
import com.example.LoginSystem.entities.LoginRTO;
import com.example.LoginSystem.entities.User;
import com.example.LoginSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class LoginService {

    public static final String JWT_SECRET = "b45f8afd-2e3b-423e-bc46-7887b3923643";
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public LoginRTO login(LoginDTO loginDTO) throws Exception {
        if (loginDTO == null) return null;
        User userInDB = userRepository.findByEmail(loginDTO.getEmail());
        if (userInDB == null || !userInDB.isActive()) return null;
        boolean canLogin = this.canUserLogin(userInDB, loginDTO.getPassword());
        if (!canLogin) return null;
        String JWT = generateJWT(userInDB);
        userInDB.setPassword(null);
        LoginRTO out = new LoginRTO();
        out.setJWT(JWT);
        out.setUser(userInDB);
        return out;
    }

    public boolean canUserLogin(User user, String password) {
        return encoder.matches(password, user.getPassword());
    }

    public String generateJWT(User user) {
        String JWT = getJWT(user);
        user.setJwtCreatedOn(LocalDateTime.now());
        userRepository.save(user);
        return JWT;
    }

    public static Date convertToDateViaInstant(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getJWT(User user) {
        Date expire = convertToDateViaInstant(LocalDateTime.now().plusDays(15));
        return JWT.create().withIssuer("develhope-demo").withIssuedAt(new Date()).withExpiresAt(expire)
                .withClaim("id", user.getId()).sign(Algorithm.HMAC512(JWT_SECRET));
    }


}
