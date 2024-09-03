package com.discovery.entities;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
@Getter
@Setter
@ToString(exclude = "password",callSuper = true) // toString excluding password
public class Account extends BaseEntity {
	@Column(length = 20)
	private String firstName;
	
	@Column(length = 20)
	private String lastName;
	
	@Column(length = 30, unique = true)
	private String email;
	
	@Column(length = 300, nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false, insertable = false, updatable = false)
	private Role role;
	
}
