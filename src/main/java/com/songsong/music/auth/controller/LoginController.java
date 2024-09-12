package com.songsong.music.auth.controller;

import com.songsong.music.auth.service.LoginService;
import com.songsong.music.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class LoginController {
    private final LoginService loginService;
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @ResponseBody
    public Map<String, String> login(UserDto userDto, HttpSession session) {
        Map<String, String> map = new HashMap<>();
        Optional<UserDto> optional = loginService.login(userDto);

        optional.ifPresentOrElse(
                user -> {
                    session.setAttribute("userDto",user);
                    map.put("result", "success");
                },

                () -> {
                    map.put("result", "fail");
                }
        );
        return map;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/pages/login";

    }
}
