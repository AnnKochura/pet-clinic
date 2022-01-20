package com.home.project.pet.clinic.properties;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PetListInterlayer {

    @NotNull(message = "name CANNOT BE NULL")
    @NotEmpty(message = "name CANNOT BE EMPTY")
    private String name;

    @NotNull(message = "chipNumber CANNOT BE NULL")
    @NotEmpty(message = "chipNumber CANNOT BE EMPTY")
    private String chipNumber;

    @NotNull(message = "earTag CANNOT BE NULL")
    @NotEmpty(message = "earTag CANNOT BE EMPTY")
    private String earTag;

    @NotNull(message = "bornDate CANNOT BE NULL")
    private Date bornDate;

    @NotNull(message = "type CANNOT BE NULL")
    @NotEmpty(message = "type CANNOT BE EMPTY")
    private String type;

    @NotNull(message = "neutering CANNOT BE NULL")
    @NotEmpty(message = "neutering CANNOT BE EMPTY")
    private String neutering;

    @NotNull(message = "breed CANNOT BE NULL")
    @NotEmpty(message = "breed CANNOT BE EMPTY")
    private String breed;

    @NotNull(message = "color CANNOT BE NULL")
    @NotEmpty(message = "color CANNOT BE EMPTY")
    private String color;

    @NotNull(message = "gender CANNOT BE NULL")
    @NotEmpty(message = "gender CANNOT BE EMPTY")
    private String gender;
}