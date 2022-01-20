package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.constant.pets.ColorPet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorPetRepository extends JpaRepository<ColorPet, Integer> {
}
