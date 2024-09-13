package com.songsong.music.playlist.service;


import com.songsong.music.playlist.dao.PlaylistDao;
import com.songsong.music.playlist.dto.PlaylistDto;
import com.songsong.music.playlist.dto.PlaylistParamDto;
import com.songsong.music.playlist.dto.PlaylistResultDto;
import com.songsong.music.user.dao.UserDao;
import com.songsong.music.user.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistDao playlistDao;
    private final UserDao userDao;

    public PlaylistServiceImpl(PlaylistDao playlistDao, UserDao userDao) {
        this.playlistDao = playlistDao;
        this.userDao = userDao;
    }

    @Override
    public PlaylistResultDto getPlaylistsByCategory(PlaylistParamDto playlistParamDto) {
        PlaylistResultDto resultDto = new PlaylistResultDto();
        List<PlaylistDto> playlists = playlistDao.selectPlaylistsByCategory(playlistParamDto);
        resultDto.setList(playlists);

        // 유저 정보를 Map에 저장
        Map<Integer, UserDto> userMap = new HashMap<>();
        Map<Integer, Integer> songCountMap = new HashMap<>();  // 유저별 노래 수 저장할 Map

        playlists.forEach(playlistDto -> {
            UserDto userDto = userDao.selectUserByUserNo(playlistDto.getUserNo());
            int songCount = playlistDao.getPlaylistCountByUserNo(playlistDto.getUserNo()); // 노래 수를 가져옴
            userMap.put(playlistDto.getUserNo(), userDto);
            songCountMap.put(playlistDto.getUserNo(), songCount);  // 노래 수 저장
        });

        resultDto.setResult("SUCCESS");
        resultDto.setUserMap(userMap);
        resultDto.setSongCountMap(songCountMap);  // 노래 수 추가

        return resultDto;
    }
  
  @Override
    public List<PlaylistDto> selectPlaylistsByUser(int userNo) {
        return playlistDao.selectPlaylistsByUser(userNo);
    }
    @Override
    public void deleteMusicFromPlaylist(int userNo, int musicId) {
        playlistDao.deleteMusicFromPlaylist(userNo, musicId);
    }
}
