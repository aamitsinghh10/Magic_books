package com.springboot.magicBooks.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class RegisterDTO {
    private String email;
    private String userName;
    private String password;
    private String role;
}
