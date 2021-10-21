package com.messyo.livraria.config;

import com.messyo.livraria.usuario.service.AuthenticationService;
import com.messyo.livraria.usuario.service.JwtTokenManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String BEARER_PREFIX = "Bearer ";
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtTokenManager jwtTokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if(request.getServletPath().equals("/api/v1/usuarios/authenticate")){
            filterChain.doFilter(request, response);
        }else{
            String email = "";
            String jwtToken = "";

            String requestTokenHeader = request.getHeader("Authorization");
            if (isTokenPresent(requestTokenHeader)) {
                jwtToken = requestTokenHeader.substring(BEARER_PREFIX.length());
                email = jwtTokenManager.getUserEmailFromToken(jwtToken);
                if (isUsernameInContext(email)) {
                    addEmailInContext(request, email, jwtToken);
                }
                filterChain.doFilter(request, response);
            } else {
//            logger.warn("JWT does not begin with Bearer String");
                filterChain.doFilter(request, response);
            }
        }
    }

    private void addEmailInContext(HttpServletRequest request, String email, String jwtToken) {
        UserDetails userDetails = authenticationService.loadUserByUsername(email);
        if (jwtTokenManager.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

    private boolean isTokenPresent(String requestTokenHeader) {
        return requestTokenHeader != null && requestTokenHeader.startsWith(BEARER_PREFIX);
    }

    private boolean isUsernameInContext(String email) {
        return email != null && SecurityContextHolder.getContext().getAuthentication() == null;
    }
}
