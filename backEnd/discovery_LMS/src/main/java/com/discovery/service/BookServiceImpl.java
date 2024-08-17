package com.discovery.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.custom_exceptions.ApiException;
import com.discovery.custom_exceptions.ResourceNotFoundException;
import com.discovery.dao.AuthorDao;
import com.discovery.dao.BookDao;
import com.discovery.dao.CategoryDao;
import com.discovery.dto.AddAuthorDTO;
import com.discovery.dto.AddBookDTO;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.AuthorDetailsDTO;
import com.discovery.dto.BookDetailsDTO;
import com.discovery.dto.UpdateBookDTO;
import com.discovery.entities.Author;
import com.discovery.entities.Book;
import com.discovery.entities.Category;

@Service
@Transactional
public class BookServiceImpl {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public List<BookDetailsDTO> getBookList(){
		
		Set<Book> newList = bookDao.getBookAndAuthorAndCategory();
		
		List<BookDetailsDTO> list = new ArrayList<>();
		
		for(Book b : newList) {
			BookDetailsDTO dto = new BookDetailsDTO(b.getId(),
					b.getTitle(),b.getDescription(),
					b.getQuantityAvailable(), b.getCategoryDetails(),
					b.getAuthorDetails());
			list.add(dto);
		}
		
		return list;
	}
	
	
	public BookDetailsDTO getBookDetails(Long id) {
		Book book = bookDao.getBookAndAuthorAndCategoryById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid book id !!!!"));
		
		BookDetailsDTO dto = new BookDetailsDTO(book.getId(), book.getTitle(), book.getDescription()
				, book.getQuantityAvailable(), book.getCategoryDetails(), book.getAuthorDetails());
		
		return dto;
		
	}
	
	public List<BookDetailsDTO> getBookDetails(String category) {
		
//		Category c = categoryDao.findByCategoryName(category)
//				.orElseThrow(() -> new ResourceNotFoundException("Invalid category name !!!!"));
		
		List<Book> bookList = bookDao.getBookAndAuthorAndCategory(category);
		
		List<BookDetailsDTO> list = new ArrayList<>();
		
		for(Book b: bookList) {
			BookDetailsDTO dto = new BookDetailsDTO(b.getId(),
					b.getTitle(),b.getDescription(),
					b.getQuantityAvailable(), b.getCategoryDetails(),
					b.getAuthorDetails());
			list.add(dto);
		}
		
		return list;
		
	}
	
	
	public ApiResponse addNewBook(AddBookDTO dto) {
		
		Category category = null;
		// 1. get category from it's id or name
		if(dto.getCategoryId() == 0 && dto.getCategoryName() == null) {
			throw new ApiException("Category id or name required");
		}else if(categoryDao.existsById(dto.getCategoryId())) {
			 	category = categoryDao.findById(dto.getCategoryId())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid category id !!!!"));
		}else {
				category = categoryDao.findByCategoryName(dto.getCategoryName())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid category name !!!!"));
		}

			
		
		// 2. get author from it's id or name
			Author author = null;
			if(authorDao.existsById(dto.getAuthorId())){
				author = authorDao.findById(dto.getAuthorId())
						.orElseThrow(() -> new ResourceNotFoundException("Invalid author id!!!"));
			}else if(authorDao.existsByAuthorName(dto.getAuthorName())) {
				author = authorDao.findByAuthorName(dto.getAuthorName())
						.orElseThrow(() -> new ResourceNotFoundException("Invalid author name!!!"));;
			}else {
				AddAuthorDTO authorDTO = new AddAuthorDTO(dto.getAuthorName());
				Author Detachedauthor = mapper.map(authorDTO, Author.class);
				author = authorDao.save(Detachedauthor);
			}
			
		
		
			Book book = mapper.map(dto, Book.class);
		
		// 3. category 1<--->* book 
			book.setBookCategory(category);
		
		// 4. author *<--->* book
			book.setAuthor(author);
		
		// 5. save book post
			Book persistentBook = bookDao.save(book);
		
			return new ApiResponse("New book added with ID=" + persistentBook.getId(), "success");
	}
	
	public ApiResponse deleteBookRecord(Long id) {
		Book book = bookDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid book id!!!!"));
		
		bookDao.delete(book);
		
		return new ApiResponse(book.getId()+ " "+book.getTitle()+ " is  Book Deleted");
		
	}
	
	public ApiResponse updateExistingBook(UpdateBookDTO dto) {
		
		Book book = null;
		
		if(dto.getBookId() == null) {
			book = bookDao.findByTitle(dto.getBookTitle())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid book title!!!!"));
		}else {
			book = bookDao.findById(dto.getBookId())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid book id!!!!"));
		}
		
		book.setTitle(dto.getBookTitle());
		book.setDescription(dto.getDescription());
		book.setQuantityAvailable(dto.getQuantity());
		Category cat = categoryDao.findByCategoryName(dto.getCategory().getCategoryName())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Category!!!!"));
		book.setBookCategory(cat);
		
		for(AuthorDetailsDTO d: dto.getAuthors()) {
			Author a = null;
			if(authorDao.existsByAuthorName(d.getAuthorName())) {
				book.setAuthor(authorDao.findByAuthorName(d.getAuthorName())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid book id!!!!")));;
			}else {
				a = new Author(d.getAuthorName());	
				book.setAuthor(a);
			}
			
		}
		
		bookDao.save(book);
		
		
		
		
		return new ApiResponse("Updated Book on id "+ book.getId()+" as "+book.getTitle());
	}
}
