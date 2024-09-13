package com.songsong.music.user.dao;

import com.songsong.music.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    UserDto selectUserById(int userNo);
    int getSongCountByUser(int userNo);
    int getUserLikeCount(int userNo);
    void incrementUserLike(int userNo);
    void decrementUserLike(int userNo);
}
