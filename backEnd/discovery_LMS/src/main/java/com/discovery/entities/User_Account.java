package com.discovery.entities;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Entity
@DiscriminatorValue("ROLE_USER")
@NoArgsConstructor
public class User_Account extends Account{
	
	private String userImagePath;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 5)
	private UserDeleteStatus isDeleted;
	
	
	@OneToMany(mappedBy = "user")
	private Set<Borrow> borrows;
	


	public String getUserImagePath() {
		return userImagePath;
	}


	public void setUserImagePath(String userImagePath) {
		this.userImagePath = userImagePath;
	}


	public UserDeleteStatus getIsDeleted() {
		return isDeleted;
	}


	public void setIsDeleted(UserDeleteStatus isDeleted) {
		this.isDeleted = isDeleted;
	}


	public Set<Borrow> getBorrows() {
		return borrows;
	}


	public void setBorrows(Set<Borrow> borrows) {
		this.borrows = borrows;
	}


	

}
