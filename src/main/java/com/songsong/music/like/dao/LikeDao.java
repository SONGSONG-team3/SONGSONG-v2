package com.songsong.music.like.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeDao {
    boolean isLiked(@Param("userFrom") int userFrom, @Param("userTo") int userTo);
    void updateLikeStatus(@Param("userFrom") int userFrom, @Param("userTo") int userTo, @Param("status") int status);
}
