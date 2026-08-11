package com.web.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>  {

	 boolean existsByTitle(String title);

	    List<Category> findByActiveTrue();

	    Page<Category> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}
