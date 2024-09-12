package com.songsong.music.like.dto;

public class LikeDto {
    private int userNo;
    private int playlistId;
    private int status; // 0: 좋아요 x, 1: 좋아요 o

    public LikeDto(){};

    public LikeDto(int userNo, int playlistId, int status) {
        this.userNo = userNo;
        this.playlistId = playlistId;
        this.status = status;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LikeDto{" +
                "userNo=" + userNo +
                ", playlistId=" + playlistId +
                ", status=" + status +
                '}';
    }
}