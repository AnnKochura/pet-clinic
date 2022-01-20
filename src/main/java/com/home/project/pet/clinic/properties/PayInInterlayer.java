package com.home.project.pet.clinic.properties;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PayInInterlayer {

    private Integer cu_id;

    @NotEmpty(message = "pin_detail not empty!")
    @NotNull(message = "pin_detail not null!")
    private String pin_detail;

    @Min(value = 1, message = "Please choose payment method")
    @Max(value = 3, message = "There can be 3 types of payment methods")
    @NotNull(message = "pin_paymentType not null!")
    private Integer pin_paymentType;

    @Min(value = 1, message = "Can be at least 0")
    @NotNull(message = "pin_price not null!")
    private Integer pin_price;


}
