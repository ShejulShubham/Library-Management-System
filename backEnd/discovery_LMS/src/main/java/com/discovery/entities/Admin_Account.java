package com.discovery.entities;


import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;

@Entity
@DiscriminatorValue("ROLE_ADMIN")
@NoArgsConstructor
public class Admin_Account extends Account{
	
	

}
