package com.home.project.pet.clinic.entity;

import com.home.project.pet.clinic.entity.listener.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Supplier extends BaseEntity<String> { //Tedarikçi

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer supplier_id;

    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @Length(min = 2, max = 30, message = "Name field can be a minimum of 2 characters and a maximum of 30 characters")
    private String supplier_name;

    @NotNull(message = "Mail cannot be Null")
    @NotEmpty(message = "Mail cannot be empty")
    @Column(unique = true)
    private String supplier_mail;

    @NotNull(message = "Wire cannot be Null")
    @NotEmpty(message = "Wire cannot be Empty")
    private String supplier_tel;

    @Column(length = 10)
    private String supplier_statu;
}
