package com.springmvc.controller;

import com.springmvc.dto.CategoryDto;
import com.springmvc.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author BAO 6/22/2023
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

  private static final String REDIRECT_CATEGORIES = "redirect:/categories";

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping({""})
  public String listCategories(Model model) {
    return "list-customers";
  }

  @GetMapping("/showForm")
  public String showFormForAdd(Model model) {
    CategoryDto categoryDto = new CategoryDto();
    List<CategoryDto> rootCategoryList = categoryService.li
    model.addAttribute("categoryDto", categoryDto);
    return "category-form";
  }

  @PostMapping("/saveCategory")
  public String saveCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
    categoryService.saveCategory(categoryDto);
    return REDIRECT_CATEGORIES;
  }
}
