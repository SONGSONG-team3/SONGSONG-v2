package com.songsong.music.auth.dao;

import com.songsong.music.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    UserDto login(String userEmail);
}
