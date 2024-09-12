package com.songsong.music.music.dto;

public class MusicDto {
    private int musicId;
    private int categoryId;
    private String musicName;
    private String musicArtist;
    private String musicLink;

    public MusicDto(){};

    public MusicDto(int musicId, int categoryId, String musicName, String musicArtist, String musicLink) {
        this.musicId = musicId;
        this.categoryId = categoryId;
        this.musicName = musicName;
        this.musicArtist = musicArtist;
        this.musicLink = musicLink;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicArtist() {
        return musicArtist;
    }

    public void setMusicArtist(String musicArtist) {
        this.musicArtist = musicArtist;
    }

    public String getMusicLink() {
        return musicLink;
    }

    public void setMusicLink(String musicLink) {
        this.musicLink = musicLink;
    }

    @Override
    public String toString() {
        return "MusicDto{" +
                "musicId=" + musicId +
                ", categoryId=" + categoryId +
                ", musicName='" + musicName + '\'' +
                ", musicArtist='" + musicArtist + '\'' +
                ", musicLink='" + musicLink + '\'' +
                '}';
    }
}