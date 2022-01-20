package com.home.project.pet.clinic.entity.constant.pets;

import com.home.project.pet.clinic.entity.Customer;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class JoinPetCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jpt_id;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "cu_id")
    Customer customer;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "pet_id")
    Pet pet;


}
