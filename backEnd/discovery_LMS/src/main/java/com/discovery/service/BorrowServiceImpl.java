package com.discovery.service;
import static com.discovery.constants.Constants.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.custom_exceptions.ApiException;
import com.discovery.custom_exceptions.ResourceNotFoundException;
import com.discovery.dao.BookDao;
import com.discovery.dao.BorrowDao;
import com.discovery.dao.FineDao;
import com.discovery.dao.UserDao;
import com.discovery.dto.AddAuthorDTO;
import com.discovery.dto.AddBookDTO;
import com.discovery.dto.AddBorrowDTO;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.BookDetailsDTO;
import com.discovery.dto.BorrowDetailsDTO;
import com.discovery.dto.CountDTO;
import com.discovery.dto.DefaulterDTO;
import com.discovery.dto.UserDetailsDTO;
import com.discovery.entities.Author;
import com.discovery.entities.Book;
import com.discovery.entities.Borrow;
import com.discovery.entities.BorrowStatus;
import com.discovery.entities.Category;
import com.discovery.entities.Fine;
import com.discovery.entities.User;
import com.discovery.entities.UserDeleteStatus;
import com.discovery.entities.UserRole;

@Service
@Transactional
public class BorrowServiceImpl {

	

	@Autowired
	private BorrowDao borrowDao;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UserDao userDao;
	
    @Autowired
    private FineDao fineDao;
	
	@Autowired
	private ModelMapper mapper;
	
	

	
	
	public CountDTO getCountDetails() {
		Long borrowCount = borrowDao.countByBorrowed();
		Long userCount = userDao.count();
		Long bookCount = bookDao.count();
		Long defaulterCount = borrowDao.findDefaultersList().stream().count();
		
		List<User> uList = userDao.findAll();
		
		List<Book> bList = bookDao.findAll();
		
		List<UserDetailsDTO> users = new ArrayList<>();
		for(User u: uList) {
			if(u.getRole() == UserRole.ROLE_ADMIN) {
				
				}else {
			UserDetailsDTO uDto = new UserDetailsDTO(u.getFirstName()+" "+u.getLastName(), u.getId(),
					u.getEmail());
			
			users.add(uDto);}
		}
		
		
		List<BookDetailsDTO> books = new ArrayList<>();
		for(Book b: bList) {
			BookDetailsDTO bDto = new BookDetailsDTO(b.getId(), b.getTitle()
					, b.getQuantityAvailable(), b.getAuthorDetails());
			books.add(bDto);
		}
		
		
		return new CountDTO(bookCount, userCount, borrowCount, defaulterCount, users, books);
		
	}
	
	
	public List<BorrowDetailsDTO> getBorrowList(){
		
		List<Borrow> newList = borrowDao.findAll();
		
		List<BorrowDetailsDTO> list = new ArrayList<>();
		for(Borrow b : newList) {
			BorrowDetailsDTO dto = new BorrowDetailsDTO(b.getId(),null, b.getBook().getTitle(),null, b.getUser().getFirstName(),
					b.getStatus(), b.getBorrowDate(), b.getReturnDate(),b.getDueDate());
			String name = b.getUser().getFirstName() + " " + b.getUser().getLastName(); 
			dto.setUserName(name);
			list.add(dto);
		}
		return list;
	}
	
	
	public BorrowDetailsDTO getBorrowDetailByUserIdAndBookId(Long userId) {

		User user = userDao.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("Invalid user id !!!!"));
					
		List<Borrow> borrowList = borrowDao.findByUser(user);
		
		if(borrowList.isEmpty())
			throw new ApiException("You have not borrowed any book yet");
		
		BorrowDetailsDTO dto = null;
		
		for(Borrow b: borrowList) {
			if(b.getStatus() == BorrowStatus.RETURNED) {}
			else {
				dto = new BorrowDetailsDTO(b.getId(),b.getBook().getId(), b.getBook().getTitle()
						,b.getUser().getId(), b.getUser().getFirstName()+" "+b.getUser().getLastName(),
						b.getStatus(), b.getBorrowDate(), b.getReturnDate(), b.getDueDate());
			}
		}
		
		if(dto == null)
			throw new ApiException("You have not borrowed any book yet");
					
		
					
		return dto;
	}
	
	public ApiResponse addNewBorrow(AddBorrowDTO dto) {
		
//		if(!dto.getBorrowDate().equals(LocalDate.now()))
//			throw new ApiException("Borrow date should be Current date");
//		else if(dto.getBorrowDate().isAfter(dto.getDueDate()))
//			throw new ApiException("Due date should be more than Borrow date");
//		

		// 1. get book from it's id
			Book book = bookDao.findById(dto.getBookId())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid book id !!!!"));
		
		// 2. get User from it's id
			User user = userDao.findById(dto.getUserId())
					.orElseThrow(() -> new ResourceNotFoundException("Invalid user id !!!!"));
			
//		//2.1 throw exception if user is deleted
//			if(user.getIsDeleted() == UserDeleteStatus.YES)
//				throw new ApiException("User is deleted");
		//2.2 throw exception if user has borrowed a book
			List<Borrow> borrowList = borrowDao.findByUser(user);
			
			for(Borrow b: borrowList) {
				if(b.getStatus() == BorrowStatus.BORROWED)
					throw new ApiException("You are Already in possession of book");
			}
			

		//2.3 set book quantity
			int quantity = book.getQuantityAvailable();
			
			if(quantity > 0)
				book.setQuantityAvailable(quantity-1);
			else
				throw new ApiException("Book is not available to borrow");
			
			Borrow borrow= new Borrow();
			borrow.setBook(book);
			borrow.setUser(user);
			borrow.setBorrowDate(dto.getBorrowDate());
			borrow.setDueDate(dto.getDueDate());
			borrow.setStatus(dto.getStatus());
		
		// 3. borrow 1<--->* book 
			borrow.setBook(book);
		
		// 4. borrow 1<--->* user
			borrow.setUser(user);;
		
			 //5. Set borrow date and due date
			// If no borrow date is provided, set it to now
	        LocalDate borrowDate = dto.getBorrowDate() != null ? dto.getBorrowDate() : LocalDate.now();
	        borrow.setBorrowDate(borrowDate);
	        borrow.setDueDate(borrowDate.plusDays(BORROW_PERIOD_DAYS));
	        
	        
		// 6. save book post
			Borrow persistentBorrow = borrowDao.save(borrow);
			 // Debugging: Log before and after decrementing the quantity
		    System.out.println("Current quantity: " + book.getQuantityAvailable());
		    book.setQuantityAvailable(book.getQuantityAvailable() - 1);
		    System.out.println("New quantity: " + book.getQuantityAvailable());
			bookDao.save(book); 
	        
		return new ApiResponse("New borrow added with ID= " + persistentBorrow.getId(), "success");
	
		
	}
	
	public List<DefaulterDTO> getDefaulterList(){
		List<Borrow> borrowList = borrowDao.findDefaultersList();
		
//		if(borrowList.isEmpty())
//			throw new ApiException("Defaulter list is empty!");
		
		List<DefaulterDTO> list = new ArrayList<>();
		
		for(Borrow b: borrowList) {
//			if(b.getStatus() == BorrowStatus.BORROWED) {
			DefaulterDTO dto = new DefaulterDTO(b.getId(), b.getBook().getTitle(),
					b.getUser().getFirstName()+" "+b.getUser().getLastName(),
					b.getStatus(), b.getBorrowDate(), b.getDueDate());
			
			list.add(dto);
//			}else {}
		}
		
		return list;
	}
	
public ApiResponse returnBook(Long uId, Long bId) {
		
		if(bId == null)
			throw new ApiException("Cannot find any borrowed book");
			
		Book book = bookDao.findById(bId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Book id !!!!"));
		
		User user = userDao.findById(uId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User id !!!!"));
		
		List<Borrow> newList = borrowDao.findByUserAndBook(user, book);
		
		
		ApiResponse api = new ApiResponse("Could not found any Record");
		
		for(Borrow b: newList) {
			if(b.getStatus() == BorrowStatus.BORROWED) {
				b.setStatus(BorrowStatus.RETURNED);
				b.setReturnDate(LocalDate.now());
				int q = book.getQuantityAvailable() + 1;
				book.setQuantityAvailable(q);
				api = new ApiResponse(b.getBook().getTitle() + " Book returned Successfully by "
						+ b.getUser().getFirstName()+" "+b.getUser().getLastName(), "success");				
			}	
				
		}
		
		return api;
		
		
	}


	public ApiResponse returnBorrowedBook(Long borrowId) {
	    // 1. Retrieve the borrow record
	    Borrow borrow = borrowDao.findById(borrowId)
	            .orElseThrow(() -> new ResourceNotFoundException("Borrow record not found"));

	    // 2. Check if the book has already been returned
	    if (borrow.getReturnDate() != null) {
	        throw new ApiException("This book has already been returned");
	    }

	    // 3. Set the return date to now
	    borrow.setReturnDate(LocalDate.now());

	    // 4. Increment the book quantity and save it
	    Book book = borrow.getBook();
	    book.setQuantityAvailable(book.getQuantityAvailable() + 1);
	    bookDao.save(book);

	    // 5. Check if a fine should be applied
	    if (borrow.getDueDate() != null && borrow.getReturnDate().isAfter(borrow.getDueDate())) {
	        long daysLate = java.time.temporal.ChronoUnit.DAYS.between(borrow.getDueDate(), borrow.getReturnDate());
	        double fineAmount = daysLate * DAILY_FINE_RATE;
	        
	        Fine fine = new Fine();
	        fine.setBorrow(borrow);
	        fine.setFineAmount(fineAmount);
	        fine.setFineDate(LocalDate.now());
	        fine.setPaid(false);
	        fineDao.save(fine);
	    }

	    // 6. Update borrow status and save the borrow record
	    borrow.setStatus(BorrowStatus.RETURNED);
	    borrowDao.save(borrow);

	    return new ApiResponse("Book '" + book.getTitle() + "' returned successfully. Fine (if any) has been applied.", "success");
	}

	

}