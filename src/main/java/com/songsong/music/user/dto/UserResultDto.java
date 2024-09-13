package com.songsong.music.user.dto;


import java.util.List;

public class UserResultDto {
    private String result;
    private MypageDto mypageDto;
    private UserDto userdto;
    private List<UserCategoryDto> userCategoryDtoList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public MypageDto getMypageDto() {
        return mypageDto;
    }

    public void setMypageDto(MypageDto mypageDto) {
        this.mypageDto = mypageDto;
    }


    public UserDto getUserdto() {
        return userdto;
    }

    public void setUserdto(UserDto userdto) {
        this.userdto = userdto;
    }

    public List<UserCategoryDto> getUserCategoryDtoList() {
        return userCategoryDtoList;
    }

    public void setUserCategoryDtoList(List<UserCategoryDto> userCategoryDtoList) {
        this.userCategoryDtoList = userCategoryDtoList;
    }
}
