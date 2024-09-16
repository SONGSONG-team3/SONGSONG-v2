package com.songsong.music.playlist.service;


import com.songsong.music.category.dto.CategoryDto;
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

        // 카테고리별 플레이리스트 목록 조회
        List<PlaylistDto> playlists = playlistDao.selectPlaylistsByCategory(playlistParamDto);
        resultDto.setList(playlists);

        // 유저 정보를 Map에 저장
        Map<Integer, UserDto> userMap = new HashMap<>();
        Map<Integer, List<CategoryDto>> userCategoryMap = new HashMap<>();  // 유저가 선택한 카테고리들
        Map<Integer, Integer> songCountMap = new HashMap<>();  // 유저별 노래 수 저장할 Map

        // 카테고리별 플레이리스트의 총 개수
        int totalCount = playlistDao.getPlaylistCountByCategory(playlistParamDto.getSearchCategory());

        // 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalCount / playlistParamDto.getLimit());
//        // 현재 페이지 플레이리스트 목록
//        List<PlaylistDto> playlistinPage = playlistDao.selectPlaylistsByCategory(playlistParamDto);

        playlists.forEach(playlistDto -> {
            UserDto userDto = userDao.selectUserByUserNo(playlistDto.getUserNo());
            int songCount = playlistDao.getPlaylistCountByUserNo(playlistDto.getUserNo()); // 노래 수를 가져옴
            userMap.put(playlistDto.getUserNo(), userDto);
            songCountMap.put(playlistDto.getUserNo(), songCount);  // 노래 수 저장

            // userNo로 사용자의 카테고리 가져오기
            List<CategoryDto> userCategories = userDao.selectCategoriesByUserNo(playlistDto.getUserNo());
            userCategoryMap.put(playlistDto.getUserNo(), userCategories); // userNo 별 카테고리 저장
        });



        resultDto.setResult("SUCCESS");
        resultDto.setUserMap(userMap);
        resultDto.setSongCountMap(songCountMap);  // 노래 수 추가
        resultDto.setUserCategoryMap(userCategoryMap);
        resultDto.setTotalCount(totalCount);
        resultDto.setTotalPages(totalPages);
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

    @Override
    public void addSongToPlaylist(PlaylistDto playlistDto) {
        playlistDao.addSongToPlaylist(playlistDto.getUserNo(), playlistDto.getMusicId());
    }
}
