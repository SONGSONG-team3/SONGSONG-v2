package com.songsong.music.playlist.dao;

import com.songsong.music.playlist.dto.PlaylistDto;
import com.songsong.music.playlist.dto.PlaylistParamDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaylistDao {
    List<PlaylistDto> selectPlaylistsByCategory(PlaylistParamDto playlistParamDto);
    int getPlaylistCountByUserNo(int userNo);
    PlaylistDto selectPlaylistDetail(int playlistId);
    int getPlaylistCountByCategory(int categoryId); // 카테고리별 전체 플레이리스트 수를 가져오는 메소드

    List<PlaylistDto> selectPlaylistsByUser(int userNo);
    void deleteMusicFromPlaylist(int userNo, int musicId);
    void addSongToPlaylist(@Param("userNo") int userNo, @Param("musicId") int musicId);
}
