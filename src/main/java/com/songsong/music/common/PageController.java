package com.songsong.music.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {


    @GetMapping("/myFeed")
    public String userFeed() {
        return "myFeed";
    }

    @GetMapping("/otherUserFeed")
    public String otherFeed() {
        return "otherUserFeed";
    }
}
