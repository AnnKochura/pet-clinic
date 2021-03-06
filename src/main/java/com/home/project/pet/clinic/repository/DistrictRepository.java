package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.constant.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query(value = "SELECT did,dname,city.cid FROM district INNER JOIN city ON city.cid = district.cid WHERE city.cid =?1", nativeQuery = true)
    List<District> getXDistricts(Integer cid);

}
