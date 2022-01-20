package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.Lab;
import com.home.project.pet.clinic.entity.projections.LabInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Integer> {
    @Query(value = "SELECT\n" +
            "\tlab_id,\n" +
            "\tlab_detail,\n" +
            "\tlab_type,\n" +
            "\tCONCAT( cu_name, ' ', cu_surname ) AS customer,\n" +
            "\tCONCAT( us_name, ' ', us_surname ) AS doctor,\n" +
            "\tlab_file \n" +
            "FROM\n" +
            "\tlab\n" +
            "\tINNER JOIN customer ON customer.cu_id = lab.cu_id\n" +
            "\tINNER JOIN `user` ON `user`.us_id = lab.us_id", nativeQuery = true)
    List<LabInfo> labPageRowsData();

    @Query(value = "SELECT\n" +
            "\tlab_id,\n" +
            "\tlab_detail,\n" +
            "\tlab_type,\n" +
            "\tCONCAT( cu_name, ' ', cu_surname ) AS customer,\n" +
            "\tCONCAT( us_name, ' ', us_surname ) AS doctor,\n" +
            "\tlab_file \n" +
            "FROM\n" +
            "\tlab\n" +
            "\tINNER JOIN customer ON customer.cu_id = lab.cu_id\n" +
            "\tINNER JOIN `user` ON `user`.us_id = lab.us_id \n" +
            "WHERE\n" +
            "\tlab_id LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR lab_detail LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR lab_type LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR CONCAT( cu_name, ' ', cu_surname ) LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR CONCAT( us_name, ' ', us_surname ) LIKE CONCAT( '%', ?1, '%' ) \n" +
            "\tOR lab_file LIKE CONCAT( '%', ?1, '%' );", nativeQuery = true)
    List<LabInfo> labPageRowsDataSearch(String search);

}
