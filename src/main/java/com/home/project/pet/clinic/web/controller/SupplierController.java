package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.Supplier;
import com.home.project.pet.clinic.repository.SupplierRepository;
import com.home.project.pet.clinic.utils.Util;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {

    final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("")
    public String supplier(Model model, Supplier supplier) {
        model.addAttribute("supplier", supplier);
        model.addAttribute("isValidName", false);
        model.addAttribute("isError", false);
        return "supplier";
    }

    Supplier supplierUpdate = new Supplier();
    Integer processType = null;

    @PostMapping("/insert")
    public String supplierInsert(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult bindingResult, Model model) {
        boolean isValidName = false;
        if (!bindingResult.hasErrors()) {
            try {
                String temp = supplier.getSupplier_statu() == null ? "0" : "1";
                supplier.setSupplier_statu(temp);
                if (processType == 1) {
                    supplierUpdate = new Supplier();
                } else {
                    supplier.setSupplier_id(supplierUpdate.getSupplier_id());
                }
                supplierRepository.saveAndFlush(supplier);
                supplierUpdate = new Supplier();
                return "redirect:/supplier";
            } catch (DataIntegrityViolationException ex) {
                Util.log("SupplierController Error : " + "The supplier e-mail is available in the system, enter another e-mail." + ex, this.getClass());
                isValidName = true;
                model.addAttribute("validNameError", "The supplier e-mail is available in the system, enter another e-mail.");
            } catch (Exception ex) {
                Util.log("supplierInsert Error : " + ex, this.getClass());
            }
        }
        model.addAttribute("isValidName", isValidName);
        model.addAttribute("isError", true);
        return "supplier";
    }

    @GetMapping("/getAllSupplier")
    @ResponseBody
    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    @GetMapping("/delete/{index}")
    @ResponseBody
    public Boolean deleteCustomerGroup(@PathVariable String index) {
        boolean isValidSupplier = false;
        try {
            Integer supplierId = Integer.parseInt(index);
            isValidSupplier = supplierRepository.existsById(supplierId);
            if (isValidSupplier) {
                supplierRepository.deleteById(supplierId);
                supplierUpdate = new Supplier();
            }
        } catch (Exception ex) {
            Util.log("deleteSupplier Error : " + ex, this.getClass());
        }
        return isValidSupplier;
    }

    @GetMapping("/update/{index}")
    @ResponseBody
    public List<Object> updateSupplierUpdate(@PathVariable String index) {
        List<Object> info = new ArrayList<>();
        try {
            Integer supplierId = Integer.parseInt(index);
            boolean isValidCustomerGroup = supplierRepository.existsById(supplierId);
            if (isValidCustomerGroup) {
                supplierUpdate = supplierRepository.findById(supplierId).get();
                info.add(true);
                info.add(supplierUpdate);
            } else {
                info.add(false);
            }
        } catch (Exception ex) {
            Util.log("updateSupplier Error : " + ex, this.getClass());
        }
        return info;
    }

    @GetMapping("/processtype/{strIndex}")
    @ResponseBody
    public boolean processType(@PathVariable String strIndex) {
        try {
            int index = Integer.parseInt(strIndex);
            if (index != 3) {
                processType = index;
            }
        } catch (Exception ex) {
            Util.log("processTypeError Error : " + ex, this.getClass());
        }
        return true;
    }

    @GetMapping("/search/{strSearch}")
    @ResponseBody
    public List<Supplier> getSupplierSearch(@PathVariable String strSearch) {
        try {
            return supplierRepository.findBySupplier_nameContainsAllIgnoreCase(strSearch.trim());
        } catch (Exception ex) {
            Util.log("supplierSearch Error : " + ex, this.getClass());
        }
        return null;
    }
}
