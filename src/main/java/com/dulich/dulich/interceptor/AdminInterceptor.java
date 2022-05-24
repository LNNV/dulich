package com.dulich.dulich.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie [] cookies = request.getCookies();
        String role = "";
        for (Cookie cookie : cookies)
            if (cookie.getName().equals("role"))
                role = cookie.getValue();
        if (role.equals("customer") || role.equals("")) {
            response.sendRedirect("/home");
            return false;
        }
        return true;
   }
}
