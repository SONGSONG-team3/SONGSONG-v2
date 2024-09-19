package com.songsong.music.user.service;

import com.songsong.music.user.dao.UserDao;

import com.songsong.music.user.dto.UserCategoryDto;
import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.dto.UserResultDto;
import com.songsong.music.user.dto.UserSignupRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }



    // 마이페이지
    @Override
    public UserResultDto detailMypage(int userNo) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            UserDto userDto = userDao.getUserById(userNo);
            List<UserCategoryDto> userCategoryDtoList = userDao.getUserCategories(userNo);

            if (userDto != null) {
                userResultDto.setResult("success");
                userResultDto.setUserdto(userDto);
                userResultDto.setUserCategoryDtoList(userCategoryDtoList);
            } else {
                userResultDto.setResult("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            userResultDto.setResult("fail");
        }
        return userResultDto;
    }


    @Override
    public UserResultDto updateUserMypage(UserDto userDto) {
        UserResultDto userResultDto = new UserResultDto();

        try {
            // Log the incoming request to verify the data
            System.out.println("Updating user: " + userDto);

            int ret = userDao.updateUserMypage(userDto);

            if (ret == 1) {
                userResultDto.setResult("success");
            } else {
                userResultDto.setResult("fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
            userResultDto.setResult("fail");
        }

        return userResultDto;
    }

    @Override
    public UserResultDto updateUserCategory(int userNo, List<Integer> categoryIds) {

        UserResultDto userResultDto = new UserResultDto();

        try{

            // 해당 userNo의 카테고리 전부 삭제
            userDao.deleteUserCategory(userNo);

            // 해당 userNo가 새로 선택한 카테고리 전부 추가
            for( Integer categoryId : categoryIds){
                UserCategoryDto userCategoryDto = new UserCategoryDto();
                userCategoryDto.setUserNo(userNo);
                userCategoryDto.setCategoryId(categoryId);

                userDao.insertUserCategory(userCategoryDto);
            }

            userResultDto.setResult("success");

        }catch (Exception e){

            e.printStackTrace();
            userResultDto.setResult("fail");

        }

        return userResultDto;
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

    @Override
    public UserDto selectUserById(int userNo) {
        return userDao.selectUserById(userNo);
    }

    @Override
    public int getSongCountByUser(int userNo) {
        return userDao.getSongCountByUser(userNo);
    }

    @Override
    public Integer getUserLikeCount(int userNo) {
        Integer likeCount = userDao.getUserLikeCount(userNo);
        return (likeCount != null) ? likeCount : 0;
    }

    @Override
    public List<String> getCategoriesByUserNo(int userNo) {
        return userDao.getCategoriesByUserNo(userNo);
    }

    @Override
    public String selectUserNicknameByUserNo(int userNo) {
        return userDao.selectUserNicknameByUserNo(userNo);
    }

}
