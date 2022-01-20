package com.home.project.pet.clinic.entity;

import com.home.project.pet.clinic.entity.listener.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class PaymentOut extends BaseEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pout_id;

    @NotEmpty(message = "Detail section cannot be empty!")
    @NotNull(message = "Detail section cannot be NULL!")
    private String pout_detail;

    @Min(value = 1, message = "Can be at least 1")
    private Integer pout_price;

    private Integer pout_operationType;

    @Min(value = 1, message = "Please choose payment method")
    @Max(value = 3, message = "There can be 3 types of payment methods")
    private Integer pout_paymentType;

}
