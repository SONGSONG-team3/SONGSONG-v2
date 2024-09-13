package com.songsong.music.playlist.service;


import com.songsong.music.playlist.dao.PlaylistDao;
import com.songsong.music.playlist.dto.PlaylistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistDao playlistDao;

    @Override
    public List<PlaylistDto> selectPlaylistsByUser(int userNo) {
        return playlistDao.selectPlaylistsByUser(userNo);
    }
    @Override
    public void deleteMusicFromPlaylist(int userNo, int musicId) {
        playlistDao.deleteMusicFromPlaylist(userNo, musicId);
    }
}