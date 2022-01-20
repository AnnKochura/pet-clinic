package com.home.project.pet.clinic.entity.security;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ro_id;

    private String ro_name;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
