package com.songsong.music.user.service;

import com.songsong.music.user.dao.UserDao;
import com.songsong.music.user.dto.UserCategoryDto;
import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.dto.UserResultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    // UserDao DI
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


}
