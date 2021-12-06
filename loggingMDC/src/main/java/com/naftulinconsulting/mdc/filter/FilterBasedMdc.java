package com.naftulinconsulting.mdc.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class FilterBasedMdc extends OncePerRequestFilter {

    private static final String CORRELATION_KEY = "cFilterId";

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws java.io.IOException, ServletException {
        try {
            final String token = UUID.randomUUID().toString();
            MDC.put(CORRELATION_KEY, token);
            chain.doFilter(request, response);
        } finally {
            MDC.remove(CORRELATION_KEY);
        }
    }

}
