package com.songsong.music.playlist.dto;

import java.time.LocalDateTime;

public class PlaylistDto {
    private int userNo;
    private int musicId;
    private boolean sameUser;
    public PlaylistDto(){};

    public PlaylistDto( int userNo, int musicId, boolean sameUser) {
        this.userNo = userNo;
        this.musicId = musicId;
        this.sameUser = sameUser;
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
    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public boolean isSameUser() {
        return sameUser;
    }

    public void setSameUser(boolean sameUser) {
        this.sameUser = sameUser;
    }
    @Override
    public String toString() {
        return "PlaylistDto{" +
                ", userNo=" + userNo +
                ", musicId=" + musicId +
                ", sameUser=" + sameUser +
                '}';
    }
}