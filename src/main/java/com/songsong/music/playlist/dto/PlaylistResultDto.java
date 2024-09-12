package com.songsong.music.playlist.dto;

import java.util.List;

public class PlaylistResultDto {
    // 요청 결과
    private String result;

    // 목록
    private List<PlaylistDto> list;

    // 상세
    private PlaylistDto dto;

    // 곡 전체 수
    private int count;

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

    @Override
    public String toString() {
        return "PlaylistResultDto{" +
                "result='" + result + '\'' +
                ", list=" + list +
                ", dto=" + dto +
                ", count=" + count +
                '}';
    }
}
