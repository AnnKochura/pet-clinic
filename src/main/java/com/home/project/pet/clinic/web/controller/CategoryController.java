package com.home.project.pet.clinic.web.controller;

import com.home.project.pet.clinic.entity.Category;
import com.home.project.pet.clinic.repository.CategoryRepository;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private static final Logger LOG = Logger.getLogger(CategoryController.class);

    final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("")
    public String category(Model model, Category category) {
        model.addAttribute("category", category);
        model.addAttribute("isValidName", false);
        model.addAttribute("isError", false);
        return "category";
    }
    Category categoryUpdate = new Category();
    Integer processType = null;

    @PostMapping("/insert")
    public String categoryInsert(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        boolean isValidName = false;
        if (!bindingResult.hasErrors()) {
            try {
                if (processType == 1) {
                    categoryUpdate = new Category();
                } else {
                    category.setCategory_id(categoryUpdate.getCategory_id());
                }
                categoryRepository.saveAndFlush(category);
                categoryUpdate = new Category();
                return "redirect:/category";
            } catch (DataIntegrityViolationException e) {
                LOG.error("Data Integrity Exception: " + e);
                isValidName = true;
                model.addAttribute("validNameError", "This category name exists in the system.");
            } catch (Exception e) {
                LOG.error("Exception during category insertion: " + e);
            }
        }
        model.addAttribute("isValidName", isValidName);
        model.addAttribute("isError", true);
        return "category";
    }

    @GetMapping("/getAllCategory")
    @ResponseBody
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @GetMapping("/delete/{index}")
    @ResponseBody
    public Boolean deleteCategory(@PathVariable String index) {
        Boolean isValidCategory = false;
        try {
            Integer categoryId = Integer.parseInt(index);
            isValidCategory = categoryRepository.existsById(categoryId);
            if (isValidCategory) {
                categoryRepository.deleteById(categoryId);
                categoryUpdate = new Category();
            }
        } catch (Exception e) {
            LOG.error("Failed to delete category: " + e);
        }
        return isValidCategory;
    }

    @GetMapping("/update/{index}")
    @ResponseBody
    public List<Object> updateCategoryUpdate(@PathVariable String index) {
        List<Object> info = new ArrayList<>();
        try {
            Integer categoryId = Integer.parseInt(index);
            boolean isValidCategory = categoryRepository.existsById(categoryId);
            if (isValidCategory) {
                categoryUpdate = categoryRepository.findById(categoryId).get();
                info.add(true);
                info.add(categoryUpdate);
            } else {
                // There is no category searched, the system has been tampered with
                info.add(false);
            }
        } catch (Exception e) {
            LOG.error("Failed to update category: " + e);
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
        } catch (Exception e) {
            LOG.error("Failed to process type: " + e);
        }
        return true;
    }

    @GetMapping("/search/{strSearch}")
    @ResponseBody
    public List<Category> getCategorySearch(@PathVariable String strSearch) {
        try {
            return categoryRepository.findByCategory_titleContainsIgnoreCase(strSearch.trim());
        } catch (Exception e) {
            LOG.error("Failed to find a category: " + e);
        }
        return null;
    }
}
