package com.levraijmk.userservice.controller;

import com.levraijmk.userservice.dto.UserDto;
import com.levraijmk.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
   private final UserService userService;


    public UserController (final UserService userService){
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto created = this.userService.createUser(userDto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto userDto = this.userService.getUserById(id);
        if(userDto==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id,@RequestBody UserDto userDto){
        try{
            this.userService.updateUser(id,userDto);
            return ResponseEntity.ok("Utilisateur mise à jour avec succès");
        }
        catch (IllegalArgumentException e){
            log.info("****** Monitoring - Mise a jour utilisateur : {}",e.getMessage());
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }



}
