package com.discovery.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.discovery.entities.Category;

public interface CategoryDao extends JpaRepository<Category,Long> {
	
	boolean existsByCategoryName(String categoryName);
	
	Optional<Category> findByCategoryName(String categoryName);
}
