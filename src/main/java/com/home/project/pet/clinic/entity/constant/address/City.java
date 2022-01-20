package com.home.project.pet.clinic.entity.constant.address;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @Column(length = 50)
    private String cname;
}
