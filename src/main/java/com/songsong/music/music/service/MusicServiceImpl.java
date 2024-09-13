package com.songsong.music.music.service;

import com.songsong.music.music.dao.MusicDao;
import com.songsong.music.music.dto.MusicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    private final MusicDao musicDao;

    public MusicServiceImpl(MusicDao musicDao) {
        this.musicDao = musicDao;
    }

//    @Override
//    public MusicDto selectMusicById(int musicId) {
//        return musicDao.selectMusicById(musicId);
//    }

    @Override
    public List<MusicDto> selectMusicByIds(List<Integer> musicIds) {
        return musicDao.selectMusicByIds(musicIds);
    }

    @Override
    public int addSong(MusicDto musicDto) {
        // 음악 추가
        musicDao.addSong(musicDto);
        // 추가된 음악 ID를 반환
        return musicDto.getMusicId();
    }
}