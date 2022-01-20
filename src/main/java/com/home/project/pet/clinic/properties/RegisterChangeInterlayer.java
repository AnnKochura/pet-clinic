package com.home.project.pet.clinic.properties;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterChangeInterlayer {

    @NotEmpty(message = "user_name not empty!")
    @NotNull(message = "user_name not null!")
    String change_user_name;

    @NotEmpty(message = "not empty")
    String[] change_user_roles;

}
