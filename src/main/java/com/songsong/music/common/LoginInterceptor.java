package com.songsong.music.common;

import com.songsong.music.user.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private final String jsonStr = "{\"result\":\"login\"}";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor >>> " + request.getRequestURI());

        HttpSession session = request.getSession();
        UserDto userDto = (UserDto) session.getAttribute("userDto");

        if (userDto == null) {
            if("true".equals(request.getHeader("ajax"))) {
                System.out.println("LoginInterceptor >>> ajax");
                response.getWriter().write(jsonStr);

            } else {
                System.out.println("LoginInterceptor >>> page");
                response.sendRedirect("/pages/login");
            }
            return false;
        }

        return true;
    }

}