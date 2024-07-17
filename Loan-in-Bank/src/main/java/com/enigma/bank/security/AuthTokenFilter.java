package com.enigma.bank.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.enigma.bank.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("Authentication Token: starting");
            String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

            String clientToken = null;
            if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
                clientToken = headerAuth.substring(7);
                log.info("Authentication Token: header notnull");
            }

            if (clientToken != null && jwtUtil.verifyJwtToken(clientToken)) {
                Map<String, String> userInfo = jwtUtil.getUserInfoByToken(clientToken);
                UserDetails user = userService.loadUserById(userInfo.get("userId"));

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                request.setAttribute("userId",userInfo.get("userId")); // cari user denga id seusui
            }
            log.info("Authentication Token: successfully authenticated");
        } catch (JWTVerificationException e) {
            log.error("Exception " + e.getMessage());
        }

        log.info("do filter");
        filterChain.doFilter(request, response);
    }
}
