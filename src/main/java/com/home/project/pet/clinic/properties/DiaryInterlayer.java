package com.home.project.pet.clinic.properties;

import lombok.Data;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DiaryInterlayer {

    @NotEmpty(message = "diary_title not empty!")
    @NotNull(message = "diary_title not null!")
    private String diary_title;

    @NotEmpty(message = "diary_description not empty!")
    @NotNull(message = "diary_description not null!")
    private String diary_description;

    @NotEmpty(message = "diary_date not empty!")
    @NotNull(message = "diary_date not null!")
    private String diary_date;

    @NotEmpty(message = "diary_time not empty!")
    @NotNull(message = "diary_time not null!")
    private String diary_time;

    private Boolean diary_statu;

    @Min(value = 1, message = "Can be at least 1")
    @NotNull(message = "us_id not null!")
    private Integer us_id;

    @Min(value = 1, message = "Can be at least 1")
    @NotNull(message = "cu_id not null!")
    private Integer cu_id;
}