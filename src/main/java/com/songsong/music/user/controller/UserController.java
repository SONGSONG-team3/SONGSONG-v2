package com.songsong.music.user.controller;

import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.dto.UserResultDto;
import com.songsong.music.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    // UserService DI
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 마이페이지
    @GetMapping("/pages/mypage/detail")
    @ResponseBody
    private UserResultDto detailBoard(HttpSession session) {

        // login 할 때 session 에 담은 객체
//        int userNo = ((UserDto) session.getAttribute("userDto")).getUserNo();
        int userNo = 1;

        return userService.detailMypage(userNo);
    }


    @PostMapping("/pages/mypage/user-update")
    @ResponseBody
    public UserResultDto updateUserMypage(@RequestBody UserDto userDto, HttpSession session) {

        // login 할 때 session 에 담은 객체
//        int userNo = ((UserDto) session.getAttribute("userDto")).getUserNo();
        int userNo = 1; // Assuming userNo is hardcoded for now
        userDto.setUserNo(userNo);

        // Log to check if userPassword is set
        System.out.println("Request to update user: " + userDto);

        // Call service to update user
        return userService.updateUserMypage(userDto);
    }


    @PostMapping("/pages/mypage/categories-update")
    @ResponseBody
    public UserResultDto updateUserCategory(@RequestBody List<Integer> categoryIds, HttpSession session) {

//        int userNo = ((UserDto) session.getAttribute("userDto")).getUserNo();
        int userNo = 1;
        return userService.updateUserCategory(userNo, categoryIds);
    }








}
