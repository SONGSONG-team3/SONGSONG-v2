package com.songsong.music.music.dao;

import com.songsong.music.music.dto.MusicDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MusicDao {
    // 음악 ID로 음악 조회
//    MusicDto selectMusicById(int musicId);
    List<MusicDto> selectMusicByIds(List<Integer> musicIds);
}
