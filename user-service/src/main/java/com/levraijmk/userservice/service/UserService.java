package com.levraijmk.userservice.service;

import com.levraijmk.userservice.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    public UserDto createUser(UserDto userDto){

        log.info("Utilisateur créer : {} ",userDto);
        return userDto;
    }
}
