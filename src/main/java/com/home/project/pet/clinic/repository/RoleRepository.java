package com.home.project.pet.clinic.repository;

import com.home.project.pet.clinic.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
