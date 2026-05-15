package com.levraijmk.userservice.service;

import com.levraijmk.userservice.dto.UserDto;
import com.levraijmk.userservice.entity.User;
import com.levraijmk.userservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto){

        log.info("Utilisateur créer : {} ",userDto);

        final User createuser = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .address(userDto.getAddress())
                .alerting(userDto.isAlerting())
                .energyAlertingThreshold(userDto.getEnergyAlertingThreshold())

                .build();
      User userCreated =  this.userRepository.save(createuser);
        return toUserDto(userCreated);

    }

    private UserDto toUserDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .address(user.getAddress())
                .alerting(user.isAlerting())
                .energyAlertingThreshold(user.getEnergyAlertingThreshold())
                .build();
    }

   public UserDto getUserById(Long id){
        return this.userRepository.findById(id)
                .map(this::toUserDto)
                .orElse(null);
   }



}
