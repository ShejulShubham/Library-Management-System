package com.discovery.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "borrows")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Borrow  extends BaseEntity {

	
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
   
    private LocalDate borrowDate;
    
  
    private LocalDate returnDate;
    
    @Enumerated(EnumType.STRING)
	@Column(length = 10)
    private BorrowStatus status;
    
    @Column(name = "due_date")
    private LocalDate dueDate;  // New field for due date

	public void setBorrowDate() {
		// TODO Auto-generated method stub
		
	}
}
