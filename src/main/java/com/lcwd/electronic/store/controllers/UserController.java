package com.lcwd.electronic.store.controllers;


import com.lcwd.electronic.store.dtos.ApiResponseMessage;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //create


    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid
            @RequestBody UserDto userDto){

        UserDto user=userService.createUser(userDto);

    return new ResponseEntity<>(user, HttpStatus.CREATED);


    }

    //update
    @PutMapping("/{userId}")
    public  ResponseEntity<UserDto> updateUser(@Valid
            @PathVariable("userId") String userId,
            @RequestBody UserDto userDto
    ){

        UserDto updatedUserDto=userService.updateUser(userDto,userId);

    return new  ResponseEntity<>(updatedUserDto,HttpStatus.OK);

    }

    //delete

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(
        @PathVariable String userId){
        userService.deleteUser(userId);
        ApiResponseMessage message= ApiResponseMessage.builder()
                .message("User Deleted Sucessfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return  new ResponseEntity<>(message,HttpStatus.OK);
    }

    //get all

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);

    }

    //get single
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable("userId") String userId){

        UserDto user=userService.getUserById(userId);

        return new ResponseEntity<>(user,HttpStatus.OK);



    }

    //get by email

    @GetMapping("/email/{emailId}")
    public ResponseEntity<UserDto>getUserByEmail(
            @PathVariable("emailId") String emailId){

        UserDto user=userService.getUserByEmail(emailId);

        return new ResponseEntity<>(user,HttpStatus.OK);



    }


    //search user


    @GetMapping("/user/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(
            @PathVariable String keywords){

//        UserDto user=userService.searchUser(keywords);

        return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);



    }
}
