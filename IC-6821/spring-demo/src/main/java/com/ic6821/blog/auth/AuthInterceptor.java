package com.ic6821.blog.auth;

import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_HEADER = "Bearer ";
    private static final String USERNAME_ATTRIBUTE = "username";
    public static final String INVALID_TOKEN_MSG = "Invalid token";
    public static final String MISSING_AUTHORIZATION_HEADER_MSG = "Missing Authorization header";

    @Autowired
    private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_HEADER)) {
            String token = authorizationHeader.substring(BEARER_HEADER.length());
            try {
                IdentityDTO identity = authService.validateToken(token);
                request.setAttribute(USERNAME_ATTRIBUTE, identity.username());
                return true;
            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write(INVALID_TOKEN_MSG);
                return false;
            }
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(MISSING_AUTHORIZATION_HEADER_MSG);
        return false;
    }
}
