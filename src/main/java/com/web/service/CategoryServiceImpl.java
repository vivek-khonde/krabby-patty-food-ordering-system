package com.web.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.web.model.Category;
import com.web.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	/*
	 * @Override public Category saveCategory(Category category) { return
	 * categoryRepository.save(category); }
	 */

	@Override
	public boolean existCategory(String title) {
		return categoryRepository.existsByTitle(title);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public boolean deleteCategory(int id) {
		if (categoryRepository.existsById(id)) {
	        categoryRepository.deleteById(id);
	        return true;
	    }
	    return false;
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public List<Category> getAllActiveCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Category> searchCategoryPagination(String name, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Category> getAllCategoryPagination(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCategory(String title, boolean active, MultipartFile file) throws IOException {
		// Save image
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/" + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());

        // Save category entity
        Category category = new Category();
        category.setTitle(title);
        category.setActive(active);
        category.setImage(fileName);

        categoryRepository.save(category);
    }
	
	// For updating an existing category
	@Override
	public void updateCategory(int id, String title, boolean active, MultipartFile file) throws IOException {
	    Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));

	    category.setTitle(title);
	    category.setActive(active);

	    if (file != null && !file.isEmpty()) {
	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        Path path = Paths.get("uploads/" + fileName);
	        Files.createDirectories(path.getParent());
	        Files.write(path, file.getBytes());
	        category.setImage(fileName);
	    }

	    categoryRepository.save(category);
	}

		
}

	

