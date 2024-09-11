package com.songsong.music.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/pages/login")
    private String login() {
        return "/login";
    }

    @GetMapping("/pages/signup")
    private String signup() {
        return "/signup";
    }
}
