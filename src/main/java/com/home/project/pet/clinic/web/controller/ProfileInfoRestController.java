package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.security.User;
import com.home.project.pet.clinic.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/profile")
public class ProfileInfoRestController {

    final UserRepository userRepository;

    public ProfileInfoRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getPhoto")
    public Map<String, Object> getPhoto() {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        String email = aut.getName(); // username

        Optional<User> optUser = userRepository.findByEmailEqualsAllIgnoreCase(email);
        Map<String, Object> hm = new LinkedHashMap<>();
        optUser.ifPresent(user -> hm.put("profilePhoto", user.getUser_file()));
        return hm;
    }
}
