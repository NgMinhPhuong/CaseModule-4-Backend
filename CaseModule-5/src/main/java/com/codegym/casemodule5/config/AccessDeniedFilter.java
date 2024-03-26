package com.codegym.casemodule5.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import org.slf4j.Logger;

@Component
public class AccessDeniedFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessDeniedFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            filterChain.doFilter(request, response);
        } catch (AccessDeniedException e) {
            // log error if needed here then redirect
            LOGGER.error("Responding with access denied error. Message - {}", e.getMessage());
            RequestDispatcher requestDispatcher =
                    getServletContext().getRequestDispatcher("/api/access-denied");
            requestDispatcher.forward(request, response);
        }
    }
}
