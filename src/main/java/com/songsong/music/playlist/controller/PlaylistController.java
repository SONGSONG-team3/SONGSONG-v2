package com.songsong.music.playlist.controller;

import com.songsong.music.playlist.dto.PlaylistParamDto;
import com.songsong.music.playlist.dto.PlaylistResultDto;
import com.songsong.music.playlist.service.PlaylistService;
import com.songsong.music.category.dao.CategoryDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/playlists")
public class PlaylistController {
    private final PlaylistService playlistService;
    private final CategoryDao categoryDao;
    public PlaylistController(PlaylistService playlistService, CategoryDao categoryDao) {
        this.playlistService = playlistService;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<PlaylistResultDto> getPlaylistsByCategory(
            @PathVariable int categoryId,
            @RequestParam(required = false) int userNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {

        String categoryName = categoryDao.selectCategoryNameById(categoryId);



        // PlaylistParamDto 생성 및 설정
        PlaylistParamDto playlistParamDto = new PlaylistParamDto();
        playlistParamDto.setSearchCategory(categoryId);
        playlistParamDto.setUserNo(userNo);
        playlistParamDto.setLimit(size);
        playlistParamDto.setOffset(page * size);

        PlaylistResultDto resultDto = playlistService.getPlaylistsByCategory(playlistParamDto);

        System.out.println("playlistcontroller 플레이리스트 결과: " + resultDto.getList());
        System.out.println("playlistcontroller 유저 정보 확인: " + resultDto.getUserMap());

        return ResponseEntity.ok(resultDto);
    }
}
