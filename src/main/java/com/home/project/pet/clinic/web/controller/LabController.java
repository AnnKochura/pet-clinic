package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.projections.LabInfo;
import com.home.project.pet.clinic.entity.security.User;
import com.home.project.pet.clinic.properties.LabInterlayer;
import com.home.project.pet.clinic.repository.LabRepository;
import com.home.project.pet.clinic.entity.Customer;
import com.home.project.pet.clinic.entity.Lab;
import com.home.project.pet.clinic.repository.CustomerRepository;
import com.home.project.pet.clinic.repository.UserRepository;
import com.home.project.pet.clinic.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/lab")
@Controller
public class LabController {

    private final String UPLOAD_DIR = "src/main/resources/static/uploads/";

    final CustomerRepository customerRepository;
    final UserRepository userRepository;
    final LabRepository labRepository;

    public LabController(CustomerRepository customerRepository, UserRepository userRepository, LabRepository labRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.labRepository = labRepository;
    }

    @GetMapping("")
    public String lab(Model model, LabInterlayer labInterlayer) {
        model.addAttribute("customerList", customerRepository.allCustomerInfos());
        model.addAttribute("doctorList", userRepository.getUsersForRoleId(2));
        model.addAttribute("labInterlayer", labInterlayer);
        model.addAttribute("isError", false);
        return "lab";
    }

    @PostMapping("/insertLab")
    public String insertLab(@Valid @ModelAttribute("labInterlayer") LabInterlayer labInterlayer, BindingResult bindingResult, Model model, @RequestParam("lab_file") MultipartFile file) {
        if (!bindingResult.hasErrors()) {
            Lab lab = new Lab();

            Integer us_id = labInterlayer.getUs_id();
            Optional<User> obtUser = userRepository.findById(us_id);
            obtUser.ifPresent(lab::setUser);

            Integer cu_id = labInterlayer.getCu_id();
            Optional<Customer> obtCustomer = customerRepository.findById(cu_id);
            obtCustomer.ifPresent(lab::setCustomer);

            Integer lab_type = labInterlayer.getLab_type();
            lab.setLab_type(lab_type);

            String lab_detail = labInterlayer.getLab_detail();
            lab.setLab_detail(lab_detail);

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String ext = "";
            try {//Checking if the image is not uploaded because the file part is not checked in validation
                int length = fileName.lastIndexOf(".");
                ext = fileName.substring(length, fileName.length());
            } catch (Exception e) {
                Util.log("LabController insertLab Error : " + e, this.getClass());
                model.addAttribute("customerList", customerRepository.allCustomerInfos());
                model.addAttribute("doctorList", userRepository.getUsersForRoleId(2));
                model.addAttribute("isError", true);
                return "lab";
            }

            String uui = UUID.randomUUID().toString();
            fileName = uui + ext;

            lab.setLab_file(fileName);

            try {
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                //add database
                labRepository.save(lab);
            } catch (IOException e) {
                Util.log("LabController Error : " + e, this.getClass());
            }
            return "redirect:/lab";
        }
        model.addAttribute("customerList", customerRepository.allCustomerInfos());
        model.addAttribute("doctorList", userRepository.getUsersForRoleId(2));
        model.addAttribute("isError", true);
        return "lab";
    }

    @GetMapping("/labList")
    @ResponseBody
    public List<LabInfo> getLabList() {
        try {
            return labRepository.labPageRowsData();
        } catch (Exception e) {
            Util.log("LabController Error : " + e, this.getClass());
        }
        return null;
    }

    @GetMapping("/labList/{search}")
    @ResponseBody
    public List<LabInfo> getLabListSearch(@PathVariable String search) {
        try {
            return labRepository.labPageRowsDataSearch(search);
        } catch (Exception e) {
            Util.log("LabController Error : " + e, this.getClass());
        }
        return null;
    }

    @GetMapping("/delete/{stLabId}")
    @ResponseBody
    public Boolean delete(@PathVariable String stLabId) {
        boolean feedBack = false;
        try {
            Integer labId = Integer.parseInt(stLabId);
            feedBack = labRepository.existsById(labId);
            if (feedBack) {
                Optional<Lab> optLab = labRepository.findById(labId);
                if (optLab.isPresent()) {
                    labRepository.deleteById(labId);
                    File file = new File(UPLOAD_DIR + optLab.get().getLab_file());
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        } catch (Exception e) {
            Util.log("LabController Error : " + e, this.getClass());
        }
        return feedBack;
    }

    @GetMapping("/getShowFile/{stLabId}")
    @ResponseBody
    public Boolean showFile(@PathVariable String stLabId) {
        boolean feedBack = false;
        try {
            Integer labId = Integer.parseInt(stLabId);
            feedBack = labRepository.existsById(labId);
        } catch (Exception e) {
            Util.log("LabController Error : " + e, this.getClass());
        }
        return feedBack;
    }
}