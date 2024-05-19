package com.lcwd.electronic.store.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @Column(name = "user_name",unique = true)
    private String name;
    @Column(name = "user_email",unique = true)
    private String email;
    @Column(name = "user_password",length = 10)
    private String password;
    private String gender;
    @Column(length = 1000)
    private String about;

    @Column(name = "user_image_name")
    private String imageName;


}
