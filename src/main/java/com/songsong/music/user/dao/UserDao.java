package com.songsong.music.user.dao;

import com.songsong.music.category.dto.CategoryDto;
import com.songsong.music.user.dto.UserCategoryDto;
import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.dto.UserSignupRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    // 메인 페이지
    @Select("SELECT * FROM user WHERE user_no = #{userNo}")
    UserDto selectUserByUserNo(int userNo);
  
    // 마이페이지
    UserDto getUserById(int userNo);
    List<UserCategoryDto> getUserCategories(int userNo);
    int updateUserMypage(UserDto userDto);
    int deleteUserCategory(int userNo);
    int insertUserCategory(UserCategoryDto userCategoryDto);

    int registerUser(UserDto userDto);
    void insertUserCategories(UserSignupRequest userSignupRequest);
    int checkEmailExists(String email);
    int checkNicknameExists(String nickname);


    UserDto selectUserById(int userNo);
    int getSongCountByUser(int userNo);
    Integer getUserLikeCount(int userNo);
    void updateUserLikeCount(@Param("userNo") int userNo, @Param("increment") int increment);
    List<String> getCategoriesByUserNo(int userNo);
    String selectUserNicknameByUserNo(int userNo);


    List<CategoryDto> selectCategoriesByUserNo(int userNo);
}
