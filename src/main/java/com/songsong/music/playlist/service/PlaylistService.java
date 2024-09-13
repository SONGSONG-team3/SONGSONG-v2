package com.songsong.music.playlist.service;

import com.songsong.music.playlist.dto.PlaylistParamDto;
import com.songsong.music.playlist.dto.PlaylistResultDto;
import com.songsong.music.playlist.dto.PlaylistDto;

import java.util.List;

public interface PlaylistService {
    // 메인페이지 카테고리 선택시 플리불러오기
    PlaylistResultDto getPlaylistsByCategory(PlaylistParamDto playlistParamDto);
  
  List<PlaylistDto> selectPlaylistsByUser(int userNo);
    void deleteMusicFromPlaylist(int userNo, int musicId);
    void addSongToPlaylist(PlaylistDto playlistDto);

}
