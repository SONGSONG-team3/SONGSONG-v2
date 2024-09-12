package com.songsong.music.user.dao;

import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.dto.UserSignupRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    int registerUser(UserDto userDto);
    void insertUserCategories(UserSignupRequest userSignupRequest);
    int checkEmailExists(String email);
    int checkNicknameExists(String nickname);

}
