package com.songsong.music.music.service;

import com.songsong.music.music.dto.MusicDto;

import java.util.List;

public interface MusicService {
//    MusicDto selectMusicById(int musicId);
    List<MusicDto> selectMusicByIds(List<Integer> musicIds);
    int addSong(MusicDto musicDto);
}
