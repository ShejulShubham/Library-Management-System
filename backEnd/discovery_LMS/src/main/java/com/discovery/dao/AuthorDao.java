package com.discovery.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.discovery.entities.Author;
import com.discovery.entities.User;

public interface AuthorDao extends JpaRepository<Author,Long> {
	
	boolean existsByAuthorName(String authorName);
	
	Optional<Author> findByAuthorName(String authorName);
}
