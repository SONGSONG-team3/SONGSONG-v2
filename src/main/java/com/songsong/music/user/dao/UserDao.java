package com.songsong.music.user.dao;

import com.songsong.music.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM user WHERE user_no = #{userNo}")
    UserDto selectUserByUserNo(int userNo);
}
