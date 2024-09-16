package com.songsong.music.like.service;

public interface LikeService {
    boolean isLiked(int userFrom, int userTo);
    void updateLikeStatus(int userFrom, int userTo, int status);
    void updateUserLikeCount(int userNo, int increment);
}
