package at.tuw.mp2.Controller;


import at.tuw.mp2.JWTToken;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.util.Optional;

import java.io.IOException;
import java.util.Arrays;

@Component
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getCookies() == null){
            request.setAttribute("logedin", false);
            filterChain.doFilter(request, response);
            return;
        }

        Optional<String> token = Arrays.stream(request.getCookies()).filter(x -> x.getName().equals("token")).map(Cookie::getValue).findFirst();
        if (!token.isPresent()) {
            request.setAttribute("logedin", false);
            filterChain.doFilter(request, response);
            return;
        }
        String tok = token.get();
        try {
            if(JWTToken.isTokenValid(tok, JWTToken.extractUsername(tok))){
                request.setAttribute("logedin", true);
                request.setAttribute("username", JWTToken.extractUsername(tok));
                filterChain.doFilter(request, response);
            }

        }catch(JwtException e){
            request.setAttribute("logedin", false);
            filterChain.doFilter(request, response);
            return;
        }
    }
}
