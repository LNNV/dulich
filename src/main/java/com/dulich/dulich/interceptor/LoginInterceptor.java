package com.dulich.dulich.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie [] cookies = request.getCookies();
        String username = "";
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("username"))
                username = cookie.getValue();
        if (username.equals("")) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
   }
}
