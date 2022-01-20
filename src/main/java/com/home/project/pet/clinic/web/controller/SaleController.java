package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.Customer;
import com.home.project.pet.clinic.entity.Product;
import com.home.project.pet.clinic.entity.Sale;
import com.home.project.pet.clinic.entity.projections.SaleInfo;
import com.home.project.pet.clinic.properties.SaleInterlayer;
import com.home.project.pet.clinic.repository.CustomerRepository;
import com.home.project.pet.clinic.repository.ProductRepository;
import com.home.project.pet.clinic.repository.SaleRepository;
import com.home.project.pet.clinic.repository.StoreRepository;
import com.home.project.pet.clinic.utils.Util;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/sale")
public class SaleController {

    final CustomerRepository cRepo;
    final SaleRepository sRepo;
    final ProductRepository pRepo;
    final StoreRepository storeRepository;

    public SaleController(CustomerRepository cRepo, SaleRepository sRepo, ProductRepository pRepo, StoreRepository storeRepository) {
        this.cRepo = cRepo;
        this.sRepo = sRepo;
        this.pRepo = pRepo;
        this.storeRepository = storeRepository;
    }

    @GetMapping("")
    public String sale(Model model, SaleInterlayer saleInterlayer) {
        model.addAttribute("saleInterlayer", saleInterlayer);
        model.addAttribute("isError", false);
        return "sale";
    }

    @GetMapping("/getAmount/{productId}")
    @ResponseBody
    public Integer getAmount(@PathVariable String productId) {
        try {
            Integer pid = Integer.parseInt(productId);
            Optional<Product> result = pRepo.findById(pid);
            if (result.isPresent()) {
                return result.get().getProduct_stokMiktari();
            } else {
                return 0;
            }
        } catch (Exception e) {
            Util.log("SaleController getAmount Error : " + e, this.getClass());
            return 0;
        }
    }

    @PostMapping("/insertSale")
    public String insertSale(@Valid @ModelAttribute("saleInterlayer") SaleInterlayer saleInterlayer, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            Sale sale = new Sale();
            sale.setSale_code(UUID.randomUUID().toString());
            sale.setSale_detail(saleInterlayer.getPNote());
            sale.setSale_number(saleInterlayer.getPAmount());
            sale.setSale_type(saleInterlayer.getPPaymentType());

            Optional<Product> optProduct = pRepo.findById(saleInterlayer.getPid());
            if (optProduct.isPresent()) {
                optProduct.get().setProduct_stokMiktari(optProduct.get().getProduct_stokMiktari() - sale.getSale_number());
                sale.setProduct(optProduct.get());
                pRepo.saveAndFlush(optProduct.get());
            }
            Optional<Customer> optCustomer = cRepo.findById(saleInterlayer.getCid());
            optCustomer.ifPresent(sale::setCustomer);
            if (optProduct.isPresent() && optCustomer.isPresent()) {
                //TUTAR HESAPLAMA
                Integer vatAmount = (optProduct.get().getProduct_satis() * optProduct.get().getProduct_kdv()) / 100;
                Integer unitPriceWithVat = optProduct.get().getProduct_satis() + vatAmount;
                int callPrice = unitPriceWithVat * saleInterlayer.getPAmount();
                int discountRate = 0;
                try {
                    discountRate = Integer.parseInt(optCustomer.get().getCu_rateOfDiscount());
                } catch (Exception e) {
                    Util.log("SaleController Error : " + e, this.getClass());
                }
                Integer total = callPrice - (callPrice * discountRate) / 100;
                sale.setSale_total(total);
            }

            sRepo.save(sale);
            return "redirect:/sale";
        }
        model.addAttribute("isError", true);
        return "sale";
    }

    @GetMapping("/getCustomerList")
    @ResponseBody
    public List<Customer> getCustomersCorrect() {
        try {
            return cRepo.findAll();
        } catch (Exception e) {
            Util.log("SaleController Error : " + e, this.getClass());
        }
        return null;
    }

    @GetMapping("/getProductList")
    @ResponseBody
    public List<Product> getProductsCorrect() {
        try {
            return pRepo.findAll();
        } catch (Exception e) {
            Util.log("SaleController Error : " + e, this.getClass());
        }
        return null;
    }

    @GetMapping("/getRows")
    @ResponseBody
    public List<SaleInfo> getRows() {
        try {
            return sRepo.getRows();
        } catch (Exception e) {
            Util.log("SaleController Error : " + e, this.getClass());
        }
        return null;
    }

    @GetMapping("/getRowsSearching/{search}")
    @ResponseBody
    public List<SaleInfo> getRows(@PathVariable String search) {
        try {
            return sRepo.getRowsSearching(search.trim());
        } catch (Exception e) {
            Util.log("SaleController Error : " + e, this.getClass());
        }
        return null;
    }

    @GetMapping("/delete/{stSaleId}")
    @ResponseBody
    public Boolean delete(@PathVariable String stSaleId) {
        boolean feedBack = false;
        try {
            Integer saleId = Integer.parseInt(stSaleId);
            feedBack = sRepo.existsById(saleId);
            if (feedBack) {
                Optional<Sale> optSale = sRepo.findById(saleId);
                if (optSale.isPresent()) {
                    optSale.get().getProduct().setProduct_stokMiktari(optSale.get().getProduct().getProduct_stokMiktari() + optSale.get().getSale_number());
                    pRepo.saveAndFlush(optSale.get().getProduct());
                }
                sRepo.deleteById(saleId);
            }
        } catch (Exception e) {
            Util.log("SaleController Error : " + e, this.getClass());
        }
        return feedBack;
    }

}