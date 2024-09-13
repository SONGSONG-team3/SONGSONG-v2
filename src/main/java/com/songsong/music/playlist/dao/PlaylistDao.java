package com.songsong.music.playlist.dao;

import com.songsong.music.playlist.dto.PlaylistDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PlaylistDao {
    List<PlaylistDto> selectPlaylistsByUser(int userNo);
    void deleteMusicFromPlaylist(int userNo, int musicId);
}
