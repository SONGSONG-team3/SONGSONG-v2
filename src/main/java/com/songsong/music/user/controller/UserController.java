package com.songsong.music.user.controller;

import com.songsong.music.user.dto.UserResultDto;
import com.songsong.music.user.dto.UserSignupRequest;
import com.songsong.music.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/signup")
    @ResponseBody
    public UserResultDto register(@RequestBody UserSignupRequest request){
        return userService.registerUser(request);
    }

}
