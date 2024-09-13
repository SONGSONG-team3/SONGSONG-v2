package com.songsong.music.playlist.service;

import com.songsong.music.playlist.dto.PlaylistDto;

import java.util.List;

public interface PlaylistService {

    List<PlaylistDto> selectPlaylistsByUser(int userNo);
    void deleteMusicFromPlaylist(int userNo, int musicId);
}