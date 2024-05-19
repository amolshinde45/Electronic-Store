package com.lcwd.electronic.store.dtos;

import com.lcwd.electronic.store.validate.AboutValid;
import com.lcwd.electronic.store.validate.ImageNameValid;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Bean;

@Getter
@AllArgsConstructor
@Setter
@NoArgsConstructor
@Builder
public class UserDto {

    private String userId;

    @Size(min = 3 ,max = 15, message = "Invalid Size/Name")
    private String name;

//    @Email(message = "invalid Email")

//    @Pattern(regexp = "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.) +[a-z]{2,5}$",message = "Invalid User Email")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = "Invalid User Email")

    private String email;

    @NotBlank(message = "password s required !!")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid Gender")
    private String gender;

//    @NotBlank(message = "write something about yourself")

    @AboutValid
    private String about;


//custom validation
    @ImageNameValid
    private String imageName;
}
