package com.discovery.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.discovery.entities.Author;
import com.discovery.entities.Book;
import com.discovery.entities.Borrow;
import com.discovery.entities.User;

public interface BorrowDao extends JpaRepository<Borrow,Long> {
	
	boolean existsByUser(User user);
	
//	boolean existsByAuthorName(String authorName);
	
//	Optional<Author> findByAuthorName(String authorName);
	
	List<Borrow> findByUser(User user);
	
	@Query("SELECT b FROM Borrow b LEFT JOIN FETCH b.user u WHERE b.status = :status")
	Optional <Borrow> findUserDetails(String status);
	
	List<Borrow> findByUserAndBook(User user, Book book);
	
	@Query("SELECT COUNT(b) FROM Borrow b WHERE b.status = 'BORROWED'" )
	Long countByBorrowed();
	
	@Query("SELECT b FROM Borrow b WHERE b.status = 'BORROWED'" )
	List<Borrow> getByBorrowed();
	
	@Query("SELECT b from Borrow b LEFT JOIN FETCH b.user LEFT JOIN FETCH b.book t WHERE CURRENT_DATE > b.dueDate")
	List<Borrow> findDefaultersList();
	
//	@Query("SELECT b from Borrow b LEFT JOIN FETCH b.user LEFT JOIN FETCH b.book t WHERE CURRENT_DATE > b.dueDate AND b.returnDate is NULL")
//	List<Borrow> findDefaultersList();
 }
