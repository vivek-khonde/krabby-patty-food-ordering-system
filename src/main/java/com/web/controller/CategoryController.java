package com.web.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.model.Category;
import com.web.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	    @PostMapping
	    public ResponseEntity<?> addCategory(
	            @RequestParam("title") String title,
	            @RequestParam("active") boolean active,
	            @RequestParam("file") MultipartFile file
	    ) {
	        try {
	            categoryService.saveCategory( title, active, file);
	            return ResponseEntity.ok("Category added");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error saving category");
	        }
	    }
	    
	 // PUT - Edit an existing category
	    @PutMapping("/{id}")
	    public ResponseEntity<?> updateCategory(
	            @PathVariable int id,
	            @RequestParam("title") String title,
	            @RequestParam("active") boolean active,
	            @RequestParam(value = "file", required = false) MultipartFile file
	    ) {
	        try {
	            categoryService.updateCategory(id, title, active, file);
	            return ResponseEntity.ok("Category updated");
	        } catch (Exception e) {
	            return ResponseEntity.status(500).body("Error updating category");
	        }
	    }
	    
	    /*// GET all categories
	    @GetMapping
	    public ResponseEntity<List<Category>> getAllCategories() {
	        List<Category> categories = categoryService.getAllCategory();
	        return ResponseEntity.ok(categories);
	    }
*/
	        // Get all categories
	        @GetMapping
	        public List<Category> getAllCategories() {
	            return categoryService.getAllCategory();
	        }

	        // Get category by ID
	        @GetMapping("/{id}")
	        public ResponseEntity<Category> getCategory(@PathVariable int id) {
	            Category cat = categoryService.getCategoryById(id);
	            if (cat == null) return ResponseEntity.notFound().build();
	            return ResponseEntity.ok(cat);
	        }

	        // Delete category
	        @DeleteMapping("/{id}")
	        public ResponseEntity<String> deleteCategory(@PathVariable int id) {
	            boolean deleted = categoryService.deleteCategory(id);
	            if (deleted) return ResponseEntity.ok("Deleted successfully");
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
	        }

			
	    }

	

	


