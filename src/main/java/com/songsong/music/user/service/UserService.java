package com.songsong.music.user.service;

import com.songsong.music.user.dto.*;

import java.util.List;

public interface UserService {

    // 마이페이지
    UserResultDto detailMypage(int userNo);
    UserResultDto updateUserMypage(UserDto userDto);
    UserResultDto updateUserCategory(int userNo, List<Integer> categoryIds);

}
