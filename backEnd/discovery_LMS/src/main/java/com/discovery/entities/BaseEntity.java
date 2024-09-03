package com.discovery.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@MappedSuperclass // to tell hib , not to create any tables n other entities will extend from it
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// creation date	
	@Column(name="created_on")
	@CreationTimestamp //adds creation date / time / time stamp for the entity
	private LocalDate createdOn;
	
	
	// updation time stamp
	@UpdateTimestamp //add last updated - date / time / time stamp for the entity
	@Column(name="updated_on")
	private LocalDate updatedOn;
	
	public BaseEntity() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDate getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDate updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + "]";
	}
}
