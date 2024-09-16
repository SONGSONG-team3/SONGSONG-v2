package com.songsong.music.user.service;

import com.songsong.music.user.dto.*;
import com.songsong.music.user.dto.UserResultDto;
import com.songsong.music.user.dto.UserSignupRequest;
import java.util.List;

public interface UserService {

    // 마이페이지
    UserResultDto detailMypage(int userNo);
    UserResultDto updateUserMypage(UserDto userDto);
    UserResultDto updateUserCategory(int userNo, List<Integer> categoryIds);
  
    UserResultDto registerUser(UserSignupRequest userSignupRequest);

    UserDto selectUserById(int userNo);
    int getSongCountByUser(int userNo);
    int getUserLikeCount(int userNo);
    List<String> getCategoriesByUserNo(int userNo);

}
