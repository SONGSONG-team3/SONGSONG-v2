package com.songsong.music.user.dao;

import com.songsong.music.user.dto.UserCategoryDto;
import com.songsong.music.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {


    // 마이페이지
//    MypageDto detailMypage(int userNo);
    UserDto getUserById(int userNo);
    List<UserCategoryDto> getUserCategories(int userNo);
    int updateUserMypage(UserDto userDto);
    int deleteUserCategory(int userNo);
    int insertUserCategory(UserCategoryDto userCategoryDto);

}
