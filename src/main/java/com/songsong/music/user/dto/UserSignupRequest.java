package com.songsong.music.user.dto;

import java.util.List;

public class UserSignupRequest {
    private UserDto userDto;
    private List<Integer> categories;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public List<Integer> getCategories() {
        return categories;
    }

    public void setCategories(List<Integer> categories) {
        this.categories = categories;
    }
}
