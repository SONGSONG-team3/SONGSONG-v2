package com.songsong.music.playlist.dao;

import com.songsong.music.playlist.dto.PlaylistDto;
import com.songsong.music.playlist.dto.PlaylistParamDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaylistDao {
    List<PlaylistDto> selectPlaylistsByCategory(PlaylistParamDto playlistParamDto);
    int getPlaylistCountByUserNo(int categoryId);
    PlaylistDto selectPlaylistDetail(int playlistId);
  
    List<PlaylistDto> selectPlaylistsByUser(int userNo);
    void deleteMusicFromPlaylist(int userNo, int musicId);
}
