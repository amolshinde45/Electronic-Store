package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public UserDto createUser(UserDto userDto) {

        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);
        // dto->entity
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user);
        //entity -> dto
        UserDto newDto = entityToDto(savedUser);
        return newDto;

//        String userId= UUID.randomUUID().toString();
//
//        //dto -> entity
//        User user= dtoToEntity(userDto);
//
//        userDto.setUserId(userId);
//
//        User saveUser = userRepository.save(user);
//
//            //entity ->dto
//        UserDto newDto= entityToDto(saveUser);
//        return newDto;
    }



    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

         //User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found with given id"));

        User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found with given id"));

        user.setName(userDto.getName());
     user.setAbout(userDto.getAbout());
     user.setGender(userDto.getGender());
     user.setPassword(userDto.getPassword());
     user.setImageName(userDto.getImageName());


     //save data
        User updateUser=userRepository.save(user);
        UserDto updatedDto=entityToDto(updateUser);


        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {


        //User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found with given id"));
        User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found with given id"));

        //delete user

        userRepository.delete(user);

    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> users=userRepository.findAll();

        List<UserDto> dtoList=users.stream().map(
                user -> entityToDto(user))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public UserDto getUserById(String userId) {

//        User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with givem id"));

        User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with given id"));

        UserDto userDto = entityToDto(user);

        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
      //  User user=userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found with given email id and password"));

        User user=userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with given email id and password"));

        UserDto userDto=entityToDto(user);

        return userDto;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {

        List<User> users=userRepository.findByNameContaining(keyword);

        List<UserDto> userDtoList= users.stream().map(
                user -> entityToDto(user)).collect(Collectors.toList());






        return userDtoList;
    }

    private UserDto entityToDto(User saveUser) {

//        UserDto userDto= UserDto.builder().
//                userId(saveUser.getUserId())
//                .name(saveUser.getName())
//                .email(saveUser.getEmail())
//                .password(saveUser.getEmail())
//                .gender(saveUser.getGender())
//                .imageName(saveUser.getImageName())
//                .about(saveUser.getAbout())
//                .build();

//        return userDto;

        return mapper.map(saveUser,UserDto.class);




//        private String userId;
//
//
//        private String name;
//
//        private String email;
//
//        private String password;
//        private String gender;
//
//        private String about;
//
//        private String imageName;

    };


    private User dtoToEntity(UserDto userDto) {

//        User user= new User();
//        user.setUserId(userDto.getUserId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        user.setGender(userDto.getGender());
//        user.setImageName(userDto.getImageName());

// return user;

        return mapper.map(userDto, User.class);

    }


}
