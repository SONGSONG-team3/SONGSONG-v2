package com.songsong.music.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/pages/mypage")
    private String mypageUpdate(){
        return "/mypage";
    }

}
