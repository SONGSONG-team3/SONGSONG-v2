package com.songsong.music.common;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

//    // 임시 세션 설정
//    @GetMapping("/")
//    public String mainPage(HttpSession session) {
//        // 임시 사용자 정보 설정
//        session.setAttribute("userName", "TestUser");  // 세션에 사용자 이름 추가
//        session.setAttribute("userNo", 1);             // 세션에 사용자 번호 추가
//
//        return "main";  // main.jsp로 이동
//    }

    // 메인 페이지 이동
    @GetMapping("/") // /pages/main -> /
    public String main() {
        return "/main";  // main.jsp로 이동
    }

    // 회원가입 페이지 이동
    @GetMapping("/pages/signup")
    public String register() {
        return "/signup";  // register.jsp로 이동
    }

    // 로그인 페이지 이동
    @GetMapping("/pages/login")
    public String login() {
        return "/login";  // login.jsp로 이동
    }


    @GetMapping("/pages/myplaylist")
    public String myPlaylist() {
        return "/myplaylist";
    }

    @GetMapping("/pages/otherplaylist")
    public String otherPlaylist() {
        return "/otherplaylist";
    }

    // 마이페이지 이동
    @GetMapping("/pages/mypage")
    private String mypage(){
        return "/mypage";
    }
}
