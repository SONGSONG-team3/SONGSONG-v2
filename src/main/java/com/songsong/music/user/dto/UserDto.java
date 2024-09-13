package com.songsong.music.user.dto;

import java.util.Date;

public class UserDto {
    private int userNo; // userNo
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userNickname;
    private String userImage;
    private Date userRegisterDate;
    private int userLike;

    public UserDto (){}

    public UserDto(int userNo, String userName, String userPassword, String userEmail, String userNickname, String userImage, Date userRegisterDate, int userLike) {
        this.userNo = userNo;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userImage = userImage;
        this.userRegisterDate = userRegisterDate;
        this.userLike = userLike;
    }
    public int getUserLike(){
        return userLike;
    }

    public void setUserLike(int userLike){
        this.userLike = userLike;

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Date getUserRegisterDate() {
        return userRegisterDate;
    }

    public void setUserRegisterDate(Date userRegisterDate) {
        this.userRegisterDate = userRegisterDate;
    }

    public int getUserLike() {
        return userLike;
    }

    public void setUserLike(int userLike) {
        this.userLike = userLike;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userNo=" + userNo +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userImage='" + userImage + '\'' +
                ", userRegisterDate=" + userRegisterDate + '\''+
                ", userLike=" + userLike +
                '}';
    }
}