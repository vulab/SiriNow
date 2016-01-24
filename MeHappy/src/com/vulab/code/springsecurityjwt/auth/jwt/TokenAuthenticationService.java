package com.vulab.code.springsecurityjwt.auth.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.vulab.code.config.TokenUtils;

@Component
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    @Autowired
    private TokenHandler tokenHandler;
    
    @Autowired
    private UserDetailsService userService;

    public TokenAuthenticationService() {
    	
        
    }
    
    public TokenAuthenticationService(String secret, UserService userService) {
    	this.userService = userService;
        tokenHandler = new TokenHandler(secret, userService);
    }

    public String addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        String token = tokenHandler.createTokenForUser(user);
        response.addHeader(AUTH_HEADER_NAME, token);
        return token;
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        final String token = request.getHeader(AUTH_HEADER_NAME);
        if (token != null && !token.equals("null")) {
        	TokenUtils tokenUtils = new TokenUtils();
        	
        	
        	final User user = (User) userService.loadUserByUsername(tokenUtils.getUserNameFromToken(token));
            //final User user = tokenHandler.parseUserFromToken(token);
            if (user != null) {
                return new UserAuthentication(user);
            }
        }
        return null;
    }
}
