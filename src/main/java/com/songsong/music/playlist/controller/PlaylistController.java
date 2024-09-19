package com.songsong.music.playlist.controller;


import java.util.Collections;
import java.util.stream.Collectors;

import com.songsong.music.like.service.LikeService;
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
    private final LikeService likeService;

    public PlaylistController(PlaylistService playlistService, UserService userService, MusicService musicService, CategoryDao categoryDao, LikeService likeService) {
        this.playlistService = playlistService;
        this.userService = userService;
        this.musicService = musicService;
        this.categoryDao = categoryDao;
        this.likeService = likeService;
    }

    @GetMapping("/mainplaylists/{categoryId}")
    public ResponseEntity<PlaylistResultDto> getPlaylistsByCategory(
            @PathVariable int categoryId,
            //@RequestParam(required = false) int userNo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {

        String categoryName = categoryDao.selectCategoryNameById(categoryId);

        // PlaylistParamDto 생성 및 설정
        PlaylistParamDto playlistParamDto = new PlaylistParamDto();
        playlistParamDto.setSearchCategory(categoryId);
        //playlistParamDto.setUserNo(userNo);
        playlistParamDto.setLimit(size);
        playlistParamDto.setOffset(page * size);

        PlaylistResultDto resultDto = playlistService.getPlaylistsByCategory(playlistParamDto);

        System.out.println("playlistcontroller 결과 상태: " + resultDto.getResult());
        System.out.println("playlistcontroller 리스트 크기: " + resultDto.getList().size());

        System.out.println("playlistcontroller 플레이리스트 결과: " + resultDto.getList());
        System.out.println("playlistcontroller 유저 정보 확인: " + resultDto.getUserMap());

        return ResponseEntity.ok(resultDto);
    }
    @GetMapping("/myplaylist")
    public String getPlaylistsByUser(HttpSession session, Model model) {
        // 세션에서 사용자 정보 가져오기
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        if (userDto == null) {
            // 세션에 사용자 정보가 없는 경우 처리
            return "redirect:/login"; // 적절한 로그인 페이지로 리다이렉트
        }
        int userNo = userDto.getUserNo();
        List<String> categories = userService.getCategoriesByUserNo(userNo);
        String categoriesString = String.join(" ", categories);
        model.addAttribute("categories", categoriesString);

        String nickName = userService.selectUserNicknameByUserNo(userNo);
        model.addAttribute("nickName", nickName);

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
            model.addAttribute("categories", categoriesString);
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

    @PostMapping("/addSong")
    public ResponseEntity<Map<String, Object>> addSong(@RequestBody MusicDto musicDto, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        try {
            // 세션에서 userDto 가져오기
            UserDto userDto = (UserDto) session.getAttribute("userDto");

            if (userDto == null) {
                response.put("success", false);
                response.put("message", "사용자 정보가 없습니다.");
                return ResponseEntity.badRequest().body(response);
            }
            // UserDto에서 userNo 추출
            Integer userNo = userDto.getUserNo();

            // 1. 음악 추가
            int musicId = musicService.addSong(musicDto);

            // 2. PlaylistDto 객체 생성
            PlaylistDto playlistDto = new PlaylistDto();
            playlistDto.setUserNo(userNo);
            playlistDto.setMusicId(musicId);

            // 사용자 여부 확인 (예: 세션과 요청 정보를 비교하여 처리)
            boolean sameUser = true; // 예시: 이 값을 실제 구현 로직에 맞게 설정

            playlistDto.setSameUser(sameUser);

            // 3. 플레이리스트에 음악 추가
            playlistService.addSongToPlaylist(playlistDto);

            response.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "오류가 발생했습니다.");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail/{userId}")
    public String getOtherPlaylists(@PathVariable int userId, HttpSession session, Model model) {
        // 현재 사용자 정보 가져오기
        UserDto currentUserDto = (UserDto) session.getAttribute("userDto");
        if (currentUserDto == null) {
            return "redirect:/login"; // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
        }
        int currentUserNo = currentUserDto.getUserNo();

        if (userId == currentUserNo) {
            return "redirect:/pages/myplaylist"; // 본인 플레이리스트 페이지로 리다이렉트
        }
        // 해당 유저 정보 가져오기
        UserDto userDto = userService.selectUserById(userId);
        if (userDto == null) {
            return "redirect:/error";
        }

        List<String> categories = userService.getCategoriesByUserNo(userId);
        String categoriesString = String.join(" ", categories);
        model.addAttribute("categories", categoriesString);

        // 유저 정보를 모델에 추가
        model.addAttribute("userDto", userDto);

        // 해당 유저의 플레이리스트 가져오기
        List<PlaylistDto> playlists = playlistService.selectPlaylistsByUser(userId);

        if (playlists.isEmpty()) {
            // 플레이리스트가 비어 있는 경우 처리
            model.addAttribute("playlists", playlists);
            model.addAttribute("musicInfo", new HashMap<Integer, MusicDto>());
            model.addAttribute("songCount", 0);
            model.addAttribute("likeCount", userService.getUserLikeCount(userId));
            model.addAttribute("isLiked", false); // 좋아요 상태 초기화
            return "otherplaylist"; // 빈 상태로 반환
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

        // 모델에 결과 추가
        model.addAttribute("playlists", playlists);
        model.addAttribute("musicInfo", musicMap);

        String otherUserName = userDto.getUserNickname();
        model.addAttribute("otherUserName", otherUserName);

        // 곡 수와 좋아요 수 가져오기
        int songCount = userService.getSongCountByUser(userId);
        int likeCount = userService.getUserLikeCount(userId);
        model.addAttribute("songCount", songCount);
        model.addAttribute("likeCount", likeCount);

        // 현재 사용자가 해당 유저를 좋아요했는지 확인
        boolean isLiked = likeService.isLiked(currentUserNo, userId);
        model.addAttribute("isLiked", isLiked);

        return "otherplaylist"; // 다른 유저의 플레이리스트를 보여줄 JSP 페이지
    }



    @PostMapping("/addToPlaylist")
    @ResponseBody
    public Map<String, Object> addToPlaylist(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            String userNoStr = request.get("userNo");
            String musicIdStr = request.get("musicId");

            if (userNoStr == null || musicIdStr == null) {
                response.put("success", false);
                response.put("message", "Invalid input: userNo or musicId is null.");
                return response;
            }

            int userNo = Integer.parseInt(userNoStr);
            int musicId = Integer.parseInt(musicIdStr);

            // PlaylistDto 객체 생성 및 추가
            PlaylistDto playlistDto = new PlaylistDto();
            playlistDto.setUserNo(userNo);
            playlistDto.setMusicId(musicId);

            playlistService.addSongToPlaylist(playlistDto);

            response.put("success", true);
            response.put("message", "Music added to playlist successfully.");
        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("message", "Invalid number format.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred: " + e.getMessage());
        }

        return response;
    }

    @PostMapping("/like")
    @ResponseBody
    public Map<String, Object> likePlaylist(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        try {
            String userFromStr = request.get("userFrom");
            String userToStr = request.get("userTo");
            String statusStr = request.get("status");

            if (userFromStr == null || userToStr == null || statusStr == null) {
                response.put("success", false);
                response.put("message", "Invalid input: one or more fields are missing.");
                return response;
            }

            int userFrom = Integer.parseInt(userFromStr);
            int userTo = Integer.parseInt(userToStr);
            int status = Integer.parseInt(statusStr);

            // 좋아요 상태를 업데이트하는 서비스 메서드 호출
            likeService.updateLikeStatus(userFrom, userTo, status);

            // 업데이트된 likeCount를 가져오는 메서드 호출
            int likeCount = userService.getUserLikeCount(userTo);

            response.put("success", true);
            response.put("likeCount", likeCount);
        } catch (NumberFormatException e) {
            response.put("success", false);
            response.put("message", "Invalid number format.");
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred: " + e.getMessage());
        }

        return response;
    }


}
