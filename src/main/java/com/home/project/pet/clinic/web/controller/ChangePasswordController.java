package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.security.User;
import com.home.project.pet.clinic.properties.ChangePasswordInterlayer;
import com.home.project.pet.clinic.services.UserService;
import com.home.project.pet.clinic.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/changepassword")
public class ChangePasswordController {

    final UserRepository userRepository;
    final UserService userService;

    public ChangePasswordController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("")
    public String changePassword(Model model, ChangePasswordInterlayer changePasswordInterlayer) {
        model.addAttribute("changePasswordInterlayer", changePasswordInterlayer);
        model.addAttribute("isError", false);
        return "changepassword";
    }


    @PostMapping("/change")
    public String changePasswordPost(@Valid @ModelAttribute("changePasswordInterlayer") ChangePasswordInterlayer changePasswordInterlayer, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Authentication aut = SecurityContextHolder.getContext().getAuthentication();
            String email = aut.getName(); // username
            Optional<User> optUser = userRepository.findByEmailEqualsAllIgnoreCase(email);
            if (optUser.isPresent()) {
                if (changePasswordInterlayer.getNewpassword().equals(changePasswordInterlayer.getNewpasswordr())) {
                    optUser.get().setPassword(userService.encoder().encode(changePasswordInterlayer.getNewpassword()));
                    userRepository.saveAndFlush(optUser.get());
                } else {
                    model.addAttribute("isError", true);
                    return "changepassword";
                }
            }
            return "redirect:/logout";
        }
        model.addAttribute("isError", false);
        return "changepassword";
    }


}


