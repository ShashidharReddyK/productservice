package com.example.productservice.dtos;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private String userName;
    private String email;
    private String password;
    private List<Role> roles;
    private boolean isEmailVerified;

}
