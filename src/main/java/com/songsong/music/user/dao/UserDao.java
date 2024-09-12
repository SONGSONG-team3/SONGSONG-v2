package com.songsong.music.user.dao;

import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.dto.UserSignupRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {
    int registerUser(UserDto userDto);
    void insertUserCategories(UserSignupRequest userSignupRequest);

}
