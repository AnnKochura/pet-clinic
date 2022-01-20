package com.home.project.pet.clinic.properties;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterInterlayer {

    @NotEmpty(message = "user_name not empty!")
    @NotNull(message = "user_name not null!")
    String user_name;

    @NotEmpty(message = "user_surname not empty!")
    @NotNull(message = "user_surname not null!")
    String user_surname;

    @NotEmpty(message = "user_email not empty!")
    @NotNull(message = "user_email not null!")
    String user_email;

    @NotEmpty(message = "user_password not empty!")
    @NotNull(message = "user_password not null!")
    String user_password;

    @NotEmpty(message = "user_tel not empty!")
    @NotNull(message = "user_tel not null!")
    String user_tel;

    @NotEmpty(message = "not empty")
    String[] user_roles;

}
