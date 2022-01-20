package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.Customer;
import com.home.project.pet.clinic.entity.CustomerGroup;
import com.home.project.pet.clinic.entity.constant.address.Address;
import com.home.project.pet.clinic.entity.constant.address.City;
import com.home.project.pet.clinic.entity.constant.address.District;
import com.home.project.pet.clinic.entity.constant.pets.*;
import com.home.project.pet.clinic.properties.CustomerInterlayer;
import com.home.project.pet.clinic.properties.CustomerPetInterlayer;
import com.home.project.pet.clinic.properties.PetListInterlayer;
import com.home.project.pet.clinic.repository.*;
import com.home.project.pet.clinic.utils.Util;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger LOG = Logger.getLogger(CustomerController.class);

    final CityRepository cityRepository;
    final DistrictRepository districtRepository;
    final ColorPetRepository colorPetRepository;
    final TypePetRepository typePetRepository;
    final BreedPetRepository breedPetRepository;
    final CustomerGroupRepository customerGroupRepository;
    final CustomerRepository customerRepository;
    final PetRepository petRepository;
    final AddressRepository addressRepository;
    final JoinPetCustomerRepository joinPetCustomerRepository;
    final JoinTypeBreedPetRepository joinTypeBreedPetRepository;


    public CustomerController(CityRepository cityRepository, DistrictRepository districtRepository, ColorPetRepository colorPetRepository, TypePetRepository typePetRepository, BreedPetRepository breedPetRepository, CustomerGroupRepository customerGroupRepository, CustomerRepository customerRepository, PetRepository petRepository, AddressRepository addressRepository, JoinPetCustomerRepository joinPetCustomerRepository, JoinTypeBreedPetRepository joinTypeBreedPetRepository) {
        this.cityRepository = cityRepository;
        this.districtRepository = districtRepository;
        this.colorPetRepository = colorPetRepository;
        this.typePetRepository = typePetRepository;
        this.breedPetRepository = breedPetRepository;
        this.customerGroupRepository = customerGroupRepository;
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
        this.addressRepository = addressRepository;
        this.joinPetCustomerRepository = joinPetCustomerRepository;
        this.joinTypeBreedPetRepository = joinTypeBreedPetRepository;
    }

    @GetMapping("")
    public String customer(Model model, CustomerPetInterlayer customerPetInterlayer) {

        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("customer", customerPetInterlayer);
        model.addAttribute("cuGroups", customerGroupRepository.findAll());

        return "customer";
    }

    @GetMapping("/getDistrict/{index}")
    @ResponseBody
    public List<District> getXDistrict(@PathVariable String index) {
        try {
            int did = Integer.parseInt(index);
            return districtRepository.getXDistricts(did);
        } catch (Exception e) {
            LOG.error("CustomerController getXDistrict Error: " + e);
        }
        return null;
    }

    @GetMapping("/getColors/")
    @ResponseBody
    public List<ColorPet> getColor() {
        return colorPetRepository.findAll();
    }

    @GetMapping("/getTypes/")
    @ResponseBody
    public List<TypePet> getAllTypePet() {
        return typePetRepository.findAll();
    }

    @GetMapping("/getBreeds/{stIndex}")
    @ResponseBody
    public List<BreedPet> getXBreeds(@PathVariable String stIndex) {
        try {
            Integer index = Integer.parseInt(stIndex);
            return breedPetRepository.getXDistricts(index);
        } catch (Exception e) {
            LOG.error("CustomerController getXBreeds Error: " + e);
        }
        return null;
    }

    @PostMapping("/add")
    @ResponseBody
    public Boolean addCustomer(@RequestBody @Valid CustomerPetInterlayer obj, BindingResult bindingResults) {
        if (!bindingResults.hasErrors()) {
            CustomerInterlayer customerDto = obj.getCustomerObj();
            Customer customer = new Customer();
            customer.setCu_name(customerDto.getCu_name());
            customer.setCu_surname(customerDto.getCu_surname());
            if (Util.isTel(customerDto.getCu_tel1())) {
                customer.setCu_tel1(customerDto.getCu_tel1());
            } else {
                return false;
            }

            System.out.println("Tel2 Format : " + Util.isTel(customerDto.getCu_tel2()));
            if (!customerDto.getCu_tel2().equals("")) {
                if (Util.isTel(customerDto.getCu_tel2())) {
                    customer.setCu_tel2(customerDto.getCu_tel2());
                } else {
                    return false;
                }
            }
            if (Util.isEmail(customerDto.getCu_mail())) {
                customer.setCu_mail(customerDto.getCu_mail());
            } else {
                return false;
            }

            customer.setCu_taxname(customerDto.getCu_taxname());
            customer.setCu_note(customerDto.getCu_note());
            customer.setCu_tcnumber(customerDto.getCu_tcnumber());
            customer.setCu_rateOfDiscount(customerDto.getCu_rateOfDiscount());
            customer.setCu_smsNotice(customerDto.getCu_smsNotice());
            customer.setCu_mailNotice(customerDto.getCu_mailNotice());
            Address address = new Address();
            Integer city_id = 0;
            if (!customerDto.getCu_cities().equals("Make Your Choice")) {
                city_id = Integer.parseInt(customerDto.getCu_cities());
            } else {
                return false;
            }
            Optional<City> optCity = cityRepository.findById(city_id);
            if (optCity.isPresent()) {
                address.setCity(optCity.get());
            }
            Integer district_id = 0;
            if (!customerDto.getCu_districts().equals("Seçim Yapınız")) {
                district_id = Integer.parseInt(customerDto.getCu_districts());
            } else {
                return false;
            }
            Optional<District> optDistrict = districtRepository.findById(district_id);
            if (optDistrict.isPresent()) {
                address.setDistrict(optDistrict.get());
            }
            address.setCu_address(customerDto.getCu_address());

            //Add Address to DB after add customer
            Address address1 = addressRepository.save(address);
            customer.setAddress(address1);

            //CUSTOMERGROUP
            Integer group_id = 0;
            if (!customerDto.getCu_group().equals("Seçim Yapınız")) {
                group_id = Integer.parseInt(customerDto.getCu_group());
            } else {
                return false;
            }
            Optional<CustomerGroup> optGroup = customerGroupRepository.findById(group_id);
            if (optGroup.isPresent()) {
                customer.setCustomerGroup(optGroup.get());
            }
            customer = customerRepository.save(customer);
            for (PetListInterlayer item : obj.getPetList()) {

                Pet pet = new Pet();

                pet.setPet_name(item.getName());
                pet.setPet_chipNumber(item.getChipNumber());
                pet.setPet_earTag(item.getEarTag());

                pet.setPet_bornDate(item.getBornDate());

                if (item.getNeutering().equals("on")) {
                    pet.setPet_neutering(true);
                } else {
                    pet.setPet_neutering(false);
                }

                if (item.getGender().equals("male")) {
                    pet.setPet_gender(true);
                } else {
                    pet.setPet_gender(false);
                }

                //Color
                Integer color_id = 0;
                if (!item.getColor().equals("0")) {
                    color_id = Integer.parseInt(item.getColor());
                } else {
                    return false;
                }
                Optional<ColorPet> optColor_pet = colorPetRepository.findById(color_id);
                if (optColor_pet.isPresent()) {
                    pet.setColorPet(optColor_pet.get());
                }
                JoinTypeBreedPet joinTypeBreedPet = new JoinTypeBreedPet();

                //Type
                Integer type_id = 0;
                if (!item.getType().equals("0")) {
                    type_id = Integer.parseInt(item.getType());
                } else {
                    return false;
                }
                System.out.println("type_id : " + type_id);
                Optional<TypePet> optType = typePetRepository.findById(type_id);
                if (optType.isPresent()) {
                    joinTypeBreedPet.setTypePet(optType.get());
                }

                //Breed
                Integer breed_id = 0;
                if (!item.getBreed().equals("0")) {
                    breed_id = Integer.parseInt(item.getBreed());
                } else {
                    return false;
                }
                Optional<BreedPet> optBreed = breedPetRepository.findById(breed_id);
                if (optBreed.isPresent()) {
                    joinTypeBreedPet.setBreedPet(optBreed.get());
                }
                joinTypeBreedPet = joinTypeBreedPetRepository.save(joinTypeBreedPet);
                pet.setJoinTypeBreedPet(joinTypeBreedPet);

                pet = petRepository.save(pet);

                JoinPetCustomer joinPetCustomer = new JoinPetCustomer();
                joinPetCustomer.setCustomer(customer);
                joinPetCustomer.setPet(pet);

                joinPetCustomerRepository.save(joinPetCustomer);
            }
            return true;
        } else {
            return false;
        }
    }
}