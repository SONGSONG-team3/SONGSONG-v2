package com.songsong.music.playlist.controller;


import java.util.stream.Collectors;
import com.songsong.music.playlist.dto.PlaylistDto;
import com.songsong.music.playlist.service.PlaylistService;
import com.songsong.music.music.dto.MusicDto;
import com.songsong.music.music.service.MusicService;
import com.songsong.music.user.dto.UserDto;
import com.songsong.music.user.service.UserService;
import com.songsong.music.playlist.dto.PlaylistParamDto;
import com.songsong.music.playlist.dto.PlaylistResultDto;
import com.songsong.music.category.dao.CategoryDao;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pages")
public class PlaylistController {

    private final PlaylistService playlistService;
    private final UserService userService;
    private final MusicService musicService;
    private final CategoryDao categoryDao;

    public PlaylistController(PlaylistService playlistService, UserService userService, MusicService musicService, CategoryDao categoryDao) {
        this.playlistService = playlistService;
        this.userService = userService;
        this.musicService = musicService;
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

        // System.out.println("playlistcontroller 플레이리스트 결과: " + resultDto.getList());
        // System.out.println("playlistcontroller 유저 정보 확인: " + resultDto.getUserMap());

        return ResponseEntity.ok(resultDto);
    }
    @GetMapping("/playlist")
    public String getPlaylistsByUser(HttpSession session, Model model) {
        // 세션에서 사용자 정보 가져오기
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        if (userDto == null) {
            // 세션에 사용자 정보가 없는 경우 처리
            return "redirect:/login"; // 적절한 로그인 페이지로 리다이렉트
        }
        int userNo = userDto.getUserNo();

        // 사용자 정보 추가
        model.addAttribute("userDto", userDto);

        // 사용자 플레이리스트 가져오기
        List<PlaylistDto> playlists = playlistService.selectPlaylistsByUser(userNo);

        if (playlists.isEmpty()) {
            // 플레이리스트가 비어 있는 경우 처리
            model.addAttribute("playlists", playlists);
            model.addAttribute("musicInfo", new HashMap<Integer, MusicDto>());
            model.addAttribute("songCount", 0);
            model.addAttribute("likeCount", userService.getUserLikeCount(userNo));
            return "myplaylist"; // 빈 상태로 반환
        }

        // 모든 musicId를 리스트로 수집
        List<Integer> musicIds = playlists.stream()
                .map(PlaylistDto::getMusicId)
                .distinct()
                .collect(Collectors.toList());

        // 여러 음악 정보를 조회
        List<MusicDto> musicList = musicService.selectMusicByIds(musicIds);

        // 음악 정보를 Map으로 변환
        Map<Integer, MusicDto> musicMap = musicList.stream()
                .collect(Collectors.toMap(MusicDto::getMusicId, musicDto -> musicDto));

        // 결과에 추가
        model.addAttribute("playlists", playlists);
        model.addAttribute("musicInfo", musicMap);

        // 곡 수와 좋아요 수 가져오기
        int songCount = userService.getSongCountByUser(userNo);
        int likeCount = userService.getUserLikeCount(userNo);
        model.addAttribute("songCount", songCount);
        model.addAttribute("likeCount", likeCount);

        return "myplaylist";
    }

    @PostMapping("/deleteMusic")
    public ResponseEntity<String> deleteMusicFromPlaylist(HttpSession session, @RequestParam("musicId") int musicId) {
        try {
            // 세션에서 사용자 정보 가져오기
            UserDto userDto = (UserDto) session.getAttribute("userDto");
            if (userDto == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 정보 없음");
            }
            int userNo = userDto.getUserNo();

            playlistService.deleteMusicFromPlaylist(userNo, musicId);
            return ResponseEntity.ok("삭제 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("삭제 실패");
        }
    }
}
