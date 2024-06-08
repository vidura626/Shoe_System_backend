package com.example.cw_spring.config;

import com.example.cw_spring.service.JwtService;
import com.example.cw_spring.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;

import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        if (StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);
            if (StringUtils.isNoneEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

                if(jwtService.istokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error("An error occurred while fetching Username from Token", e);
        } catch (ExpiredJwtException e) {
            logger.warn("The token has expired", e);
        } catch(SignatureException e){
            logger.error("Authentication Failed. Username or Password not valid.");
        }



        filterChain.doFilter(request,response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/api/v1/user/signup") || request.getServletPath().equals("/api/v1/user/signin");
    }
}
