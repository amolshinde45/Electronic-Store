package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.dtos.PegeableResponse;
import com.lcwd.electronic.store.dtos.UserDto;
import com.lcwd.electronic.store.entities.User;
import com.lcwd.electronic.store.exception.ResourceNotFoundException;
import com.lcwd.electronic.store.helper.Helper;
import com.lcwd.electronic.store.repositories.UserRepository;
import com.lcwd.electronic.store.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service



public class UserServiceImpl implements UserService {


    Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private ModelMapper mapper;

    @Value("${user.profile.image.path}")
    private String imagePath;

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

    //delete user
    @Override
    public void deleteUser(String userId)  {


        //User user=userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found with given id"));
        User user=userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not Found with given id"));


        //delete user profile image

        String fullPath=imagePath+user.getImageName();

        try {
            Path path= Paths.get(fullPath);
            Files.delete(path);

            logger.info("Image Deleted {}",path);
        } catch (NoSuchFileException ex) {
            logger.error("User image not found in folder");
            throw new RuntimeException(ex);


        }

        catch (IOException e) {

            e.printStackTrace();
        }


        userRepository.delete(user);

    }

    @Override
    public PegeableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

       // Sort sort = Sort.by(sortBy);

        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber-1,pageSize,sort);


      Page<User> page=userRepository.findAll(pageable);
        PegeableResponse<UserDto> response = Helper.getPegeableResponse(page, UserDto.class);
//        List<User> users=page.getContent();
//
//        List<UserDto> dtoList=users.stream().map(
//                user -> entityToDto(user))
//                .collect(Collectors.toList());
//
//        PegeableResponse<UserDto> response= new PegeableResponse<>();
//        response.setContent(dtoList);
//        response.setPageNumber(page.getNumber());
//        response.setPageSize(page.getSize());
//        response.setTotalElements(page.getTotalElements());
//        response.setTotalPages(page.getTotalPages());
//        response.setLastpage(page.isLast());

        return response;
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
