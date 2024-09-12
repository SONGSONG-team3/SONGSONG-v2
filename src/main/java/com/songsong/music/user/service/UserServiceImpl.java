package com.songsong.music.user.service;

import com.songsong.music.user.dao.UserDao;
import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.dto.UserResultDto;
import com.songsong.music.user.dto.UserSignupRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Override
    public UserResultDto registerUser(UserSignupRequest userSignupRequest) {
        UserResultDto userResultDto = new UserResultDto();

        // 1. 이메일 중복 체크
        int emailCount = userDao.checkEmailExists(userSignupRequest.getUserDto().getUserEmail());
        if (emailCount > 0) {
            userResultDto.setResult("email_exists");
            return userResultDto;
        }

        // 2. 닉네임 중복 체크
        int nicknameCount = userDao.checkNicknameExists(userSignupRequest.getUserDto().getUserNickname());
        if (nicknameCount > 0) {
            userResultDto.setResult("nickname_exists");
            return userResultDto;
        }

        int newUser = userDao.registerUser(userSignupRequest.getUserDto());
        if(newUser > 0) {
            UserDto userDto = userSignupRequest.getUserDto();
            System.out.println("생성된 사용자 Id: "+ userDto.getUserNo());
            userDao.insertUserCategories(userSignupRequest); // 해당 유저가 선택한 카테고리 목록 저장
            userResultDto.setResult("success");
        } else {
            userResultDto.setResult("fail");
        }
        return userResultDto;
    }
}
