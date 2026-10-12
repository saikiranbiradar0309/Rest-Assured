package com.app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CatagoryDTO;
import com.app.entities.Categories;
import com.app.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping(consumes = "application/json")
	public ResponseEntity<?> saveCategory(@RequestBody CatagoryDTO category) {
		System.out.println("In Category");
		return new ResponseEntity<>(categoryService.saveCategory(category), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<?> getAllCategories() {
		return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {

		categoryService.deleteCategory(categoryId);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Categories> getCategory(@PathVariable Long categoryId) {

		Optional<Categories> category = categoryService.getCategory(categoryId);

		return new ResponseEntity<>(category.get(), HttpStatus.OK);
		

	}

//	@GetMapping(path = "jira")
//	public String get() {
//		return "hello..";
//	}

}
