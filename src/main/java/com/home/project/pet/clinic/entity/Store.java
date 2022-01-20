package com.home.project.pet.clinic.entity;

import com.home.project.pet.clinic.entity.listener.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Store extends BaseEntity<String> {//Depo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer store_id;

    private String store_name;
    private String store_tel;
    private Boolean store_statu;

}
