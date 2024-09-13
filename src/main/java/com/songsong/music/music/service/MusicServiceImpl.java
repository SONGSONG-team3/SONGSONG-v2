package com.songsong.music.music.service;

import com.songsong.music.music.dao.MusicDao;
import com.songsong.music.music.dto.MusicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicDao musicDao;

//    @Override
//    public MusicDto selectMusicById(int musicId) {
//        return musicDao.selectMusicById(musicId);
//    }

    @Override
    public List<MusicDto> selectMusicByIds(List<Integer> musicIds) {
        return musicDao.selectMusicByIds(musicIds);
    }
}