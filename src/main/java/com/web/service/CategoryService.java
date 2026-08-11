package com.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.web.model.Category;

public interface CategoryService {

	/* public Category saveCategory(Category category); */
	public void saveCategory(String title, boolean active, MultipartFile file) throws IOException;
	
	public void updateCategory(int id, String title, boolean active, MultipartFile file) throws IOException;
		
	public boolean existCategory(String title);
	
	public List<Category> getAllCategory();
	
	public boolean deleteCategory(int id);
	
	public Category getCategoryById(int id);
	
	public List<Category> getAllActiveCategory();
	
	public Page<Category> searchCategoryPagination(String title, int pageNo, int pageSize);
	
	public Page<Category> getAllCategoryPagination(Integer pageNo, Integer pageSize);

}
