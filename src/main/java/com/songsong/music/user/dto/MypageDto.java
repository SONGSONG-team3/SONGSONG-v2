package com.songsong.music.user.dto;

import java.util.Date;
import java.util.List;

public class MypageDto {

    private UserDto userDto;
    private List<Integer> categoryIds;

    public MypageDto(){}

    public MypageDto(UserDto userDto, List<Integer> categoryIds) {
        this.userDto = userDto;
        this.categoryIds = categoryIds;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
