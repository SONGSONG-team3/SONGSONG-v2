package com.songsong.music.user.service;

import com.songsong.music.user.dto.UserResultDto;
import com.songsong.music.user.dto.UserSignupRequest;

public interface UserService {
    UserResultDto registerUser(UserSignupRequest userSignupRequest);
}
