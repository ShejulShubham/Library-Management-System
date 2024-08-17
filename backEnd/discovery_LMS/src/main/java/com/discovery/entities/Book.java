package com.discovery.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.discovery.dto.AuthorDetailsDTO;
import com.discovery.dto.CategoryDetailsDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book extends BaseEntity{
	
//    title
//    author
//    category
//    publication_date
//    ISBN
//    quantity_available
//    description
	
	@Column(name = "title", length = 30, unique = true)
	private String title;
	
//	private LocalDate publishDate;
	

	 private int quantityAvailable;

	    // Getter for quantityAvailable
	    public int getQuantityAvailable() {
	        return quantityAvailable;
	    }

	    // Setter for quantityAvailable
	    public void setQuantityAvailable(int quantityAvailable) {
	        this.quantityAvailable = quantityAvailable;
	    }
	@Column(length = 300)
	private String description;
	
	// book *--->1 Category
	@ManyToOne
	//to customize FK col name n not null constraint
	@JoinColumn(name="category_id",nullable = false)
	private Category bookCategory;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "book_author",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "author_id"))
    private Set<Author> authors = new HashSet<>();
	
	
	@OneToMany(mappedBy = "book")
    private Set<Borrow> borrows;
	
	private String bookImagePath;
	
	public String setAuthor(Author author) {
		if(authors.contains(author))
			return "author exists";
			
		authors.add(author);
		
		return "Success";
	}
	
	public List<AuthorDetailsDTO> getAuthorDetails() {
		
		List<AuthorDetailsDTO> list = new ArrayList<>();
		
		for(Author a : authors) {
			AuthorDetailsDTO newAuthor = new AuthorDetailsDTO();
			newAuthor.setAuthorId(a.getId());
			newAuthor.setAuthorName(a.getAuthorName());
			list.add(newAuthor);
		}
		
		return list;
	}
	
	
	public CategoryDetailsDTO getCategoryDetails() {
		
		CategoryDetailsDTO d = new CategoryDetailsDTO();
		d.setCategoryId(bookCategory.getId());
		d.setCategoryName(bookCategory.getCategoryName());
		
		return d;
	}


	
	
	
	
}
