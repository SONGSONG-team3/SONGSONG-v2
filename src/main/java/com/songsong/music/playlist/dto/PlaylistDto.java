package com.songsong.music.playlist.dto;

import java.time.LocalDateTime;

public class PlaylistDto {
    private int userNo;
    private int musicId;

    public PlaylistDto(){};

    public PlaylistDto(int userNo, int musicId) {
        this.userNo = userNo;
        this.musicId = musicId;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public int getMusicId() {
        return musicId;
    }

    @Override
    public String toString() {
        return "PlaylistDto{" +
                ", userNo=" + userNo +
                ", musicId=" + musicId +
                '}';
    }
}