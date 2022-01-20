package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    @Query("select s from Supplier s where upper(s.supplier_name) like upper(concat('%', ?1, '%'))")
    List<Supplier> findBySupplier_nameContainsAllIgnoreCase(String supplier_name);
}