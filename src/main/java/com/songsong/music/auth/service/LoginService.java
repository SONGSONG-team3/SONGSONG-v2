package com.songsong.music.auth.service;


import com.songsong.music.user.dto.UserDto;

import java.util.Optional;

public interface LoginService {
    Optional<UserDto> login(UserDto userDto);
}
