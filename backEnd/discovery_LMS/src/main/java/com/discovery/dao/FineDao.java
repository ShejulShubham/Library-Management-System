package com.discovery.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.discovery.entities.Borrow;
import com.discovery.entities.Fine;


public interface FineDao extends JpaRepository<Fine, Long> {
	
	List<Fine> findAll(); // Fetch all fines

	@Query("SELECT f FROM Fine f JOIN f.borrow b WHERE b.user.id = :userId")
	List<Fine> findFinesByUserId(@Param("userId") Long userId);
//    List<Fine> findDefaulters();
    
	@Query("SELECT f.user, f.fineAmount FROM Fine f WHERE f.paid = false")
	List<Long> findBorrowIdsWithUnpaidFines();
	
	Fine findByBorrow(Borrow borrow);

	
}

