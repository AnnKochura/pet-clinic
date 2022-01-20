package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.Customer;
import com.home.project.pet.clinic.repository.CustomerRepository;
import com.home.project.pet.clinic.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@RequestMapping("/customerlist")
@Controller
public class CustomerListController {

    final CustomerRepository customerRepository;

    public CustomerListController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @GetMapping("")
    public String customerList() {
        return "customerlist";
    }

    @GetMapping("/getAllCustomer")
    @ResponseBody
    public List<Customer> getAllCustomer() {
        return customerRepository.getCustomerLastThirty();
    }

    @GetMapping("/show/{index}")
    @ResponseBody
    public Customer showCustomer(@PathVariable String index) {
        try {
            Integer customerId = Integer.parseInt(index);
            Optional<Customer> customer = customerRepository.findById(customerId);
            return customer.get();
        } catch (Exception ex) {
            Util.log("CustomerListController showCustomer Error : " + ex, this.getClass());
            return null;
        }


    }

    @GetMapping("/search/{strSearch}")
    @ResponseBody
    public List<Customer> getCustomerSearch(@PathVariable String strSearch) {
        try {
            return customerRepository.searchCustomer(strSearch.trim());
        } catch (Exception ex) {
            Util.log("CustomerListController getCustomerSearch Error : " + ex, this.getClass());
        }
        return null;
    }


}