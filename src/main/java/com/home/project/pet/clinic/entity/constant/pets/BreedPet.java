package com.home.project.pet.clinic.entity.constant.pets;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BreedPet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer br_id;

    private String br_name;

    private Integer type_pet_id;
}
