package com.songsong.music.playlist.dao;

import com.songsong.music.playlist.dto.PlaylistDto;
import com.songsong.music.playlist.dto.PlaylistParamDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaylistDao {
    List<PlaylistDto> selectPlaylistsByCategory(PlaylistParamDto playlistParamDto);
    int getPlaylistCountByUserNo(int categoryId);
    PlaylistDto selectPlaylistDetail(int playlistId);
  
    List<PlaylistDto> selectPlaylistsByUser(int userNo);
    void deleteMusicFromPlaylist(int userNo, int musicId);
    void addSongToPlaylist(@Param("userNo") int userNo, @Param("musicId") int musicId);
}
