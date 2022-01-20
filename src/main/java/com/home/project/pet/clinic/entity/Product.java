package com.home.project.pet.clinic.entity;

import com.home.project.pet.clinic.entity.listener.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product extends BaseEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;
    private String product_name;
    private Integer product_unit;
    private Integer product_kdv;
    private Integer product_alis;
    private Integer product_satis;
    private Integer product_stokMiktari;
    private Boolean product_statu;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
    Category category;


}
