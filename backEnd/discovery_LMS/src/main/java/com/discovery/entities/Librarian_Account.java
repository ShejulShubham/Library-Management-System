package com.discovery.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("ROLE_LIBRARIAN")
@NoArgsConstructor
public class Librarian_Account extends Account{
	
	

}
