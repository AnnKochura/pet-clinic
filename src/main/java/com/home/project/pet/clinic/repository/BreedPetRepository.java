package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.constant.pets.BreedPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BreedPetRepository extends JpaRepository<BreedPet, Integer> {
    @Query(value = "select * from Breed_Pet where type_pet_id = ?1",nativeQuery = true)
    List<BreedPet> getXDistricts(Integer type_pet_id);
}
