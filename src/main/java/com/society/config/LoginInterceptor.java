/*package com.society.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String uri = request.getRequestURI();

        boolean needsLogin =
                uri.startsWith("/secretary") ||
                uri.startsWith("/meetings")  ||
                uri.startsWith("/transactions") ||
                uri.startsWith("/attendance");

        if (!needsLogin) return true;

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("secretary") == null) {
            response.sendRedirect("/secretary-login/SECRETARY");
            return false;                               
        }
        return true;
    }
}
*/