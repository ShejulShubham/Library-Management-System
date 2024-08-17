package com.discovery.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity{

	@Column(name = "name", length = 30, unique = true)
	private String categoryName;
	

//	@OneToMany(mappedBy = "bookCategory", 
//			cascade = CascadeType.ALL /* ,fetch = FetchType.EAGER */ ,
//			orphanRemoval = true)
//	private List<Book>books = new ArrayList<>();
	
	
	
	
}
