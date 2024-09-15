package com.songsong.music.playlist.dto;

import com.songsong.music.category.dto.CategoryDto;
import com.songsong.music.user.dto.UserDto;

import java.util.List;
import java.util.Map;

public class PlaylistResultDto {
    // 요청 결과
    private String result;

    // 목록
    private List<PlaylistDto> list;

    // 상세
    private PlaylistDto dto;

    // 곡 전체 수
    private int count;

    // 유저 정보를 저장하는 Map (userNo -> UserDto)
    private Map<Integer, UserDto> userMap;

    private Map<Integer, Integer> songCountMap;

    private Map<Integer, List<CategoryDto>> userCategoryMap;

    // 페이지네이션을 위한 전체 레코드 수
    private int totalCount;

    // 메인 페이지 - 총 페이지 수
    private int totalPages;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<PlaylistDto> getList() {
        return list;
    }

    public void setList(List<PlaylistDto> list) {
        this.list = list;
    }

    public PlaylistDto getDto() {
        return dto;
    }

    public void setDto(PlaylistDto dto) {
        this.dto = dto;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    // userMap getter, setter
    public Map<Integer, UserDto> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<Integer, UserDto> userMap) {
        this.userMap = userMap;
    }

    public Map<Integer, Integer> getSongCountMap() {
        return songCountMap;
    }

    public void setSongCountMap(Map<Integer, Integer> songCountMap) {
        this.songCountMap = songCountMap;
    }

    public Map<Integer, List<CategoryDto>> getUserCategoryMap() {
        return userCategoryMap;
    }

    public void setUserCategoryMap(Map<Integer, List<CategoryDto>> userCategoryMap) {
        this.userCategoryMap = userCategoryMap;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PlaylistResultDto{" +
                "result='" + result + '\'' +
                ", list=" + list +
                ", dto=" + dto +
                ", count=" + count +
                ", userMap=" + userMap +
                '}';
    }

}
