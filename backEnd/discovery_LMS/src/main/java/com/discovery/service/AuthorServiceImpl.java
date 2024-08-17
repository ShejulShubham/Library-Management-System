package com.discovery.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.custom_exceptions.ApiException;
import com.discovery.dao.AuthorDao;
import com.discovery.dto.AddAuthorDTO;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.AuthorDetailsDTO;
import com.discovery.entities.Author;

@Service
@Transactional
public class AuthorServiceImpl {
	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<AuthorDetailsDTO> getAuthorList(){
		
		List<Author> newList = authorDao.findAll();
		
		List<AuthorDetailsDTO> list = new ArrayList<>();
		for(Author a : newList) {
			AuthorDetailsDTO dto = new AuthorDetailsDTO(a.getId(), a.getAuthorName());
			list.add(dto);
		}
		
		return list;
	}
	
	
	public ApiResponse addAuthor(AddAuthorDTO dto) {
		
		if(authorDao.existsByAuthorName(dto.getAuthorName()))
			throw new ApiException("Author Already Exist !!!");
		
		
		Author author = mapper.map(dto, Author.class);
		
		
		Author persistentAuthor = authorDao.save(author);
		
		return new ApiResponse("New Author added with ID=" + persistentAuthor.getId());
	}
}
