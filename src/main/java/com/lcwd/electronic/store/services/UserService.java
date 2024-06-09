package com.lcwd.electronic.store.services;

import com.lcwd.electronic.store.dtos.PegeableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;

import java.io.IOException;
import java.util.List;

public interface UserService {


    //Create User


    UserDto createUser(UserDto userDto);



    //Update User
    UserDto updateUser(UserDto userDto,String userId);


    //delete User
    void deleteUser(String userId) throws IOException;

        //Get All Userss

    PegeableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);


    //Get single user by id
    UserDto getUserById(String userId);

    //get User by Email

    UserDto getUserByEmail(String email);


    //search user
    List<UserDto> searchUser(String keyword);

}
