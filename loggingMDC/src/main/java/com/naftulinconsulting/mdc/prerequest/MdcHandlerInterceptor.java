package com.naftulinconsulting.mdc.prerequest;

import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * This interceptor will be run after all the filters, e.g it would have data about authenticated user, for example
 */
public class MdcHandlerInterceptor implements HandlerInterceptor {

    private static final String CORRELATION_KEY = "cInterceptorId";
    private static final String USER_KEY = "userId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = UUID.randomUUID().toString();
        MDC.put(CORRELATION_KEY, token);
        String userName = request.getUserPrincipal() != null && request.getUserPrincipal().getName() != null ? request.getUserPrincipal().getName() : "-";
        MDC.put(USER_KEY, userName);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(CORRELATION_KEY);
        MDC.remove(USER_KEY);
    }
}
