package com.home.project.pet.clinic.properties;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;


@Data
public class CustomerPetInterlayer {
    @Valid
    private CustomerInterlayer customerObj;
    @Valid
    private List<PetListInterlayer> petList;
}
