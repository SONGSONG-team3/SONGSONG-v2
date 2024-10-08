package com.songsong.music.playlist.dto;

// playlist 목록 가져오기
// 등록, 수정은 PlaylistParamDto 대신 PlaylistDto 를 이용한다.
public class PlaylistParamDto {
    // 목록
    private int limit;
    private int offset;
    private int searchCategory;

    // 상세
    private int userNo; // 현재 사용자 userNo

    public PlaylistParamDto(){};

    public PlaylistParamDto(int limit, int offset, int searchCategory, int userNo) {
        this.limit = limit;
        this.offset = offset;
        this.searchCategory = searchCategory;
        this.userNo = userNo;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(int searchCategory) {
        this.searchCategory = searchCategory;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "PlaylistParamDto{" +
                "limit=" + limit +
                ", offset=" + offset +
                ", searchCategory='" + searchCategory + '\'' +
                ", userNo=" + userNo +
                '}';
    }
}
