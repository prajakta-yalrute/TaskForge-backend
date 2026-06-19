package com.taskforge.backend.security;

import com.taskforge.backend.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    // Skip JWT check for these APIs
   @Override
protected boolean shouldNotFilter(
        HttpServletRequest request) {

    String path = request.getServletPath();

    return path.startsWith("/api/auth/")
            || path.startsWith("/api/files/")
            || path.startsWith("/swagger-ui/")
            || path.startsWith("/v3/api-docs")
            || path.startsWith("/ws")

            // Add these
            || path.startsWith("/api/tasks/")
            || path.equals("/api/tasks")

            || path.startsWith("/api/projects/")
            || path.equals("/api/projects")

            || path.startsWith("/api/teams/")
            || path.equals("/api/teams");
}
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader =
                request.getHeader("Authorization");

        if (authHeader == null
                || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(
                    request,
                    response);
            return;
        }

        String token =
                authHeader.substring(7);

        if (jwtService.isValid(token)) {

            String email =
                    jwtService.extractEmail(token);

            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(email);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

            auth.setDetails(userDetails);

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(auth);

            System.out.println(
                    "Authenticated: " + email);
        }

        filterChain.doFilter(
                request,
                response);
    }
}