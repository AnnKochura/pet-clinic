package com.home.project.pet.clinic.properties;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CustomerInterlayer {

    @NotNull(message = "cu_name CANNOT BE NULL")
    @NotEmpty(message = "cu_name CANNOT BE EMPTY")
    private String cu_name;

    @NotNull(message = "cu_surname CANNOT BE NULL")
    @NotEmpty(message = "cu_surname CANNOT BE EMPTY")
    private String cu_surname;

    @NotNull(message = "cu_tel1 CANNOT BE NULL")
    @NotEmpty(message = "cu_tel1 CANNOT BE EMPTY")
    private String cu_tel1;

    private String cu_tel2;

    @NotNull(message = "cu_mail CANNOT BE NULL")
    @NotEmpty(message = "cu_mail CANNOT BE EMPTY")
    private String cu_mail;

    private String cu_taxname;

    private String cu_note;

    @NotNull(message = "cu_tcnumber CANNOT BE NULL")
    @NotEmpty(message = "cu_tcnumber CANNOT BE EMPTY")
    private String cu_tcnumber;

    @NotNull(message = "cu_cities CANNOT BE NULL")
    @NotEmpty(message = "cu_cities CANNOT BE EMPTY")
    private String cu_cities;

    @NotNull(message = "cu_districts CANNOT BE NULL")
    @NotEmpty(message = "cu_districts CANNOT BE EMPTY")
    private String cu_districts;

    @NotNull(message = "cu_group CANNOT BE NULL")
    @NotEmpty(message = "cu_group CANNOT BE EMPTY")
    private String cu_group;

    @NotNull(message = "cu_address CANNOT BE NULL")
    @NotEmpty(message = "cu_address CANNOT BE EMPTY")
    private String cu_address;

    @NotNull(message = "cu_rateOfDiscount CANNOT BE NULL")
    @NotEmpty(message = "cu_rateOfDiscount CANNOT BE EMPTY")
    private String cu_rateOfDiscount;

    @NotNull(message = "cu_smsNotice CANNOT BE NULL")
    @NotEmpty(message = "cu_smsNotice CANNOT BE EMPTY")
    private String cu_smsNotice;

    @NotNull(message = "cu_mailNotice CANNOT BE NULL")
    @NotEmpty(message = "cu_mailNotice CANNOT BE EMPTY")
    private String cu_mailNotice;

}
