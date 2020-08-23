package com.vishakha.treasurehunt.model;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class User {
    @Email(message = "Email address is not valid")
    private String email;
}
