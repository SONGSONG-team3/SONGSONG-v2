package com.songsong.music.like.service;

import com.songsong.music.like.dao.LikeDao;
import com.songsong.music.user.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {
    private final LikeDao likeDao;
    private final UserDao userDao;


    public LikeServiceImpl(LikeDao likeDao, UserDao userDao) {
        this.likeDao = likeDao;
        this.userDao = userDao;
    }

    @Override
    public boolean isLiked(int userFrom, int userTo) {
        return likeDao.isLiked(userFrom, userTo);
    }

    @Override
    public void updateLikeStatus(int userFrom, int userTo, int status) {
        likeDao.updateLikeStatus(userFrom, userTo, status);
        // 좋아요 상태가 1일 때는 user_like 증가
        // 상태가 0일 때는 user_like 감소
        int increment = (status == 1) ? 1 : -1;
        userDao.updateUserLikeCount(userTo, increment);
    }

    @Override
    public void updateUserLikeCount(int userNo, int increment) {
        userDao.updateUserLikeCount(userNo, increment);
    }
}
