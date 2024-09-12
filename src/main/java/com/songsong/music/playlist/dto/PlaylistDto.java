package com.songsong.music.playlist.dto;

import java.time.LocalDateTime;

public class PlaylistDto {
    private int playlistId;
    private int userNo;
    private int musicId;
    private int playlistLike; // 좋아요수 count
    private boolean sameUser;

    public PlaylistDto(){};

    public PlaylistDto(int playlistId, int userNo, int musicId, int playlistLike, boolean sameUser) {
        this.playlistId = playlistId;
        this.userNo = userNo;
        this.musicId = musicId;
        this.playlistLike = playlistLike;
        this.sameUser = sameUser;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
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

    public int getPlaylistLike() {
        return playlistLike;
    }

    public void setPlaylistLike(int playlistLike) {
        this.playlistLike = playlistLike;
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
                "playlistId=" + playlistId +
                ", userNo=" + userNo +
                ", musicId=" + musicId +
                ", playlistLike=" + playlistLike +
                ", sameUser=" + sameUser +
                '}';
    }
}