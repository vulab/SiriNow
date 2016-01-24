package com.vulab.code.springsecurityjwt.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.vulab.code.springsecurityjwt.support.validation.StringConditions;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public final class TokenHandler {

    private  String secret;
    @Autowired
    private UserDetailsService userService;
    
    public TokenHandler(){
    	
    }
    

    public TokenHandler(String secret, UserService userService) {
        this.secret = StringConditions.checkNotBlank(secret);
        this.userService = Preconditions.checkNotNull(userService);
    }

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
               // .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return (User) userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
