package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.constant.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
