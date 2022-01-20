package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.constant.pets.Pet;
import com.home.project.pet.clinic.entity.projections.AllCustomerPetInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    @Query(value = "SELECT\n" +
            "CONCAT( c.cu_name, ' ', c.cu_surname ) AS cuname,\n" +
            "c.cu_mail AS cumail,\n" +
            "p.pet_born_date AS born,\n" +
            "p.pet_chip_number AS chipnumber,\n" +
            "p.pet_gender AS gender,\n" +
            "pet_name AS pname,\n" +
            "p.pet_neutering AS neutering,\n" +
            "tp.ty_name AS typename,\n" +
            "bt.br_name AS breedname \n" +
            "FROM\n" +
            "\tcustomer AS c\n" +
            "\tINNER JOIN join_pet_customer AS j ON j.cu_id = c.cu_id\n" +
            "\tINNER JOIN pet AS p ON p.pet_id = j.pet_id\n" +
            "\tINNER JOIN join_type_breed_pet AS jtbp ON jtbp.jtbp_id = p.join_type_breed_pet\n" +
            "\tINNER JOIN breed_pet AS bt ON jtbp.br_id = bt.br_id\n" +
            "\tINNER JOIN type_pet AS tp ON tp.ty_id = jtbp.ty_id \n" +
            "WHERE\n" +
            "\tc.cu_id = ?1", nativeQuery = true)
    List<AllCustomerPetInfo> getCustomerPets(Integer id);
}
