package com.songsong.music.auth.service;

import com.songsong.music.auth.dao.LoginDao;
import com.songsong.music.user.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{
    private final LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public Optional<UserDto> login(UserDto dto) {
        UserDto userDto = loginDao.login(dto.getUserEmail());
        if(userDto != null && dto.getUserPassword().equals(userDto.getUserPassword())) {
            userDto.setUserPassword(null); // password 삭제
            return Optional.of(userDto);
        }
        return Optional.empty();
    }
}
