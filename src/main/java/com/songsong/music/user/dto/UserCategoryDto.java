package com.songsong.music.user.dto;

public class UserCategoryDto {
    private int userNo;      // Foreign key from user table
    private int categoryId;  // Foreign key from category table


    public UserCategoryDto() {}
    public UserCategoryDto(int userNo, int categoryId) {
        this.userNo = userNo;
        this.categoryId = categoryId;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "UserCategoryDto{" +
                "userNo=" + userNo +
                ", categoryId=" + categoryId +
                '}';
    }
}
