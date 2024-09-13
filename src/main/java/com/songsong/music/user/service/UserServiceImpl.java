package com.songsong.music.user.service;

import com.songsong.music.user.dao.UserDao;
import com.songsong.music.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto selectUserById(int userNo) {
        return userDao.selectUserById(userNo);
    }

    @Override
    public int getSongCountByUser(int userNo) {
        return userDao.getSongCountByUser(userNo);
    }

    @Override
    public int getUserLikeCount(int userNo) {
        return userDao.getUserLikeCount(userNo);
    }

    @Override
    public void incrementUserLike(int userNo) {
        userDao.incrementUserLike(userNo);
    }

    @Override
    public void decrementUserLike(int userNo) {
        userDao.decrementUserLike(userNo);
    }
}
