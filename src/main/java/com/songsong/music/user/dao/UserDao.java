package com.songsong.music.user.dao;


import com.songsong.music.user.dto.UserCategoryDto;
import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.dto.UserSignupRequest;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    // 마이페이지
    UserDto getUserById(int userNo);
    List<UserCategoryDto> getUserCategories(int userNo);
    int updateUserMypage(UserDto userDto);
    int deleteUserCategory(int userNo);
    int insertUserCategory(UserCategoryDto userCategoryDto);

    int registerUser(UserDto userDto);
    void insertUserCategories(UserSignupRequest userSignupRequest);
    int checkEmailExists(String email);
    int checkNicknameExists(String nickname);


}
