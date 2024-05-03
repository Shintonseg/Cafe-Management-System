package com.taskmanagement.Task_management_system.config;

import com.taskmanagement.Task_management_system.services.jwt.UserService;
import com.taskmanagement.Task_management_system.utils.JwtUtil;
import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;



@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        final String userEmail;
        final String jwtToken;

        if (StringUtils.isEmpty(authorizationHeader) || !StringUtils.startsWith(authorizationHeader,"Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
            jwtToken = authorizationHeader.substring(7);
            userEmail = jwtUtil.extractUsername(jwtToken);

        if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailService().loadUserByUsername(userEmail);

            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
