package com.home.project.pet.clinic.properties;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductInterlayer {

    private Integer product_id;

    @NotNull(message = "Product Name Cannot Be Null!")
    @NotEmpty(message = "Product Name Cannot Be Blank!")
    private String product_name;

    @Min(value = 1, message = "There Can Be At Least 1!")
    @NotNull(message = "product_unit not null!")
    private Integer product_unit;

    @Min(value = 1, message = "There Can Be At Least 1!")
    @NotNull(message = "category not null!")
    private Integer category;

    @Min(value = 1, message = "There Can Be At Least 1!")
    @NotNull(message = "product_kdv not null!")
    private Integer product_kdv;

    @Min(value = 1, message = "There Can Be At Least 1!")
    @NotNull(message = "product_alis not null!")
    private Integer product_alis;

    @NotNull(message = "product_satis not null!")
    @Min(value = 1, message = "There Can Be At Least 1!")
    private Integer product_satis;

    @NotNull(message = "product_stokMiktari not null!")
    @Min(value = 1, message = "There Can Be At Least 1!")
    private Integer product_stokMiktari;

    private Boolean product_statu;
}
