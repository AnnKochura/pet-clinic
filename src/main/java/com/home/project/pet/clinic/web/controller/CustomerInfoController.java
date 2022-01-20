package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.projections.AllCustomerPetInfo;
import com.home.project.pet.clinic.repository.PetRepository;
import com.home.project.pet.clinic.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CustomerInfoController {

    final PetRepository petRepository;
    final ProfileInfoRestController profileInfoRestController;

    public CustomerInfoController(PetRepository petRepository, ProfileInfoRestController profileInfoRestController) {
        this.petRepository = petRepository;
        this.profileInfoRestController = profileInfoRestController;
    }

    @GetMapping("/customerinfo/{stCid}")
    public String customerinfo(@PathVariable String stCid, Model model) {
        int cid = 0;

        try {
            cid = Integer.parseInt(stCid);
        } catch (Exception e) {
            Util.log("CustomerInfoController Error " + e, this.getClass());
            return "redirect:/customerlist";
        }

        String value = "/images/profiles/anonim.jpg";
        model.addAttribute("profilePhoto", value);

        List<AllCustomerPetInfo> allCustomerPetInfoList = petRepository.getCustomerPets(cid);
        allCustomerPetInfoList.forEach(item -> {
            System.out.println(item);
        });
        // The customer must have at least 1 pet to open the screen.
        //This customer's pets must have race, type, color, etc.
        if (allCustomerPetInfoList == null || allCustomerPetInfoList.size() == 0) {
            return "redirect:/customerlist";
        }
        model.addAttribute("all", allCustomerPetInfoList);
        return "customerinfo";
    }
}
