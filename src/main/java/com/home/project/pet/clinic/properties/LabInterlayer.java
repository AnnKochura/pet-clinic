package com.home.project.pet.clinic.properties;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LabInterlayer {

    @Max(value = 3, message = "There can be 3 types of labs")
    @Min(value = 1, message = "Can be at least 1")
    private Integer lab_type;

    @NotEmpty(message = "lab_detail not empty!")
    @NotNull(message = "lab_detail not null!")
    private String lab_detail;

    @Min(value = 1, message = "Can be at least 1")
    @NotNull(message = "cu_id not null!")
    private Integer cu_id;

    @Min(value = 1, message = "Can be at least 1")
    @NotNull(message = "us_id not null!")
    private Integer us_id;
}
