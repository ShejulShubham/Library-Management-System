package com.discovery.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.custom_exceptions.ApiException;
import com.discovery.dao.CategoryDao;
import com.discovery.dto.AddCategoryDTO;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.CategoryDetailsDTO;
import com.discovery.entities.Category;

@Service
@Transactional
public class CategoryServiceImpl {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public List<CategoryDetailsDTO> getCategoryList(){
		
		List<Category> newList = categoryDao.findAll();
		List<CategoryDetailsDTO> list = new ArrayList<>();
		for(Category c : newList) {
			CategoryDetailsDTO dto = new CategoryDetailsDTO(c.getId(),c.getCategoryName());
			list.add(dto);
		}
		
		return list;
	}
	
	
	public ApiResponse addCategory(AddCategoryDTO dto) {
		
		if(categoryDao.existsByCategoryName(dto.getCategoryName()))
			throw new ApiException("Category Already Exist !!!");
		
		
		Category category = mapper.map(dto, Category.class);
		
		
		Category persistentCategory = categoryDao.save(category);
		
		return new ApiResponse("New Category added with ID=" + persistentCategory.getId(), "success");
	} 
}
