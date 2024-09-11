package com.songsong.music.common;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // 메인 페이지 이동
    @GetMapping("/") // /pages/main -> /
    public String main() {
        return "main";  // main.jsp로 이동
    }

    // 회원가입 페이지 이동
    @GetMapping("/pages/user")
    public String register() {
        return "register";  // register.jsp로 이동
    }

    // 로그인 페이지 이동
    @GetMapping("/pages/login")
    public String login() {
        return "login";  // login.jsp로 이동
    }

    // 게시판 페이지 이동
    @GetMapping("/pages/board")
    public String board() {
        return "board";  // board.jsp로 이동
    }
    @GetMapping("/myFeed")
    public String userFeed() {
        return "myFeed";
    }

    @GetMapping("/otherUserFeed")
    public String otherFeed() {
        return "otherUserFeed";
    }
}
