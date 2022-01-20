package com.home.project.pet.clinic.properties;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChangePasswordInterlayer {

    @NotEmpty(message = "New password not empty!")
    @NotNull(message = "New password not null!")
    @Length(min = 4, max = 10, message = "New password can be a minimum of 4 characters and a maximum of 10 characters.")
    private String newpassword;

    @NotEmpty(message = "New password repeat not empty!")
    @NotNull(message = "New password repeat not null!")
    @Length(min = 4, max = 10, message = "New password repeat can be a minimum of 4 characters and a maximum of 10 characters.")
    private String newpasswordr;

}
