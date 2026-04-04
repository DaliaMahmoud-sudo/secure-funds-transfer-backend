package com.example.demo.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
// generate constructor for final fields
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;

    @Override
    // runs for every http request before it reaches controller
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        //extract jwt from header
        String authHeader = request.getHeader("Authorization");
        System.out.println("JWT FILTER RUNNING");
        //if no token -> return
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            System.out.println("JWT FILTER isn't RUNNING");
            return;
        }
        //extarct username from token payload

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);

        //username exist and not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            User user = userRepo.findByUsername(username).orElseThrow();
            //Create Authentication Object
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    null,
                    List.of() // authorities empty no roles
            );

            //now considered logged in
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        //Pass request to next filter If you DON'T call it Request will STOP here
        filterChain.doFilter(request, response);
    }
}