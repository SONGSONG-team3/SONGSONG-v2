package com.songsong.music.user.service;

import com.songsong.music.user.dto.UserDto;

public interface UserService {
    UserDto selectUserById(int userNo);
    int getSongCountByUser(int userNo);
    int getUserLikeCount(int userNo);
    void incrementUserLike(int userNo);
    void decrementUserLike(int userNo);
}
