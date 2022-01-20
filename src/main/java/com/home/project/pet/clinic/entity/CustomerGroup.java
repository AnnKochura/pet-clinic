package com.home.project.pet.clinic.entity;

import com.home.project.pet.clinic.entity.listener.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class CustomerGroup extends BaseEntity<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cu_gr_id;

    @Column(length = 30, unique = true)
    @NotEmpty(message = "Group Name cannot be empty!")
    @NotNull(message = "Group Name cannot be NULL!")
    @Length(min = 3, max = 30, message = "Group name can be a minimum of 3 characters and a maximum of 30 characters.")
    private String cu_gr_name;
}
