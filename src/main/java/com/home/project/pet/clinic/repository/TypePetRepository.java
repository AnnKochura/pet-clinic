package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.constant.pets.TypePet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePetRepository extends JpaRepository<TypePet, Integer> {

}
