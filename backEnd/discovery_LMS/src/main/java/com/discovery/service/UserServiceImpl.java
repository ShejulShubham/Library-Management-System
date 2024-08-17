package com.discovery.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import com.discovery.custom_exceptions.ApiException;
import com.discovery.custom_exceptions.AuthenticationException;
import com.discovery.custom_exceptions.ResourceNotFoundException;
import com.discovery.dao.BorrowDao;
import com.discovery.dao.UserDao;
import com.discovery.dto.ApiResponse;
import com.discovery.dto.SignInRequest;
import com.discovery.dto.SignUp;
import com.discovery.dto.UpdateUser;
import com.discovery.dto.UserDetailsDTO;
import com.discovery.dto.GetAllUserDetailsDTO;
import com.discovery.entities.User;
import com.discovery.entities.UserDeleteStatus;
import com.discovery.entities.UserRole;

@Service
@Transactional
public class UserServiceImpl {
	// depcy
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BorrowDao borrowDao;
		
	@Autowired
	private ModelMapper mapper;
	
	
	public List<GetAllUserDetailsDTO> getAllUsers(){
			
		List<User> newList = userDao.findAll();
			
		List<GetAllUserDetailsDTO> list = new ArrayList<>();
			
		for(User u : newList) {
//			String book;
//			BorrowStatus status;
//			if(borrowDao.existsByUser(u)) {
//				Borrow borrow = borrowDao.findUserDetails("BORROWED").orElseThrow(() -> 
//					new AuthenticationException("Invalid Email or Password !!!!!!"));
//				
//				book = borrow.getBook().getTitle();
//				status = borrow.getStatus();
//			}else {
//				book = "";
//				status = BorrowStatus.NOTHING; 
//			}	
			if(u.getRole() == UserRole.ROLE_ADMIN) {
				
			}else {
			GetAllUserDetailsDTO dto = new GetAllUserDetailsDTO(
					u.getId(), u.getFirstName()+" "+ u.getLastName(), u.getEmail()
					, "success");
			
			list.add(dto);}
			
		}
		
		return list;
		
	}

	public UserDetailsDTO authenticateUser(SignInRequest dto) {
		// 1.invoke dao 's method
		User user = userDao.findByEmailAndPassword(
					dto.getEmail(), dto.getPassword())
					.orElseThrow(() -> 
					new AuthenticationException("Invalid Email or Password !!!!!!"));
		
		if(user.getIsDeleted() == UserDeleteStatus.YES) {
			throw new ApiException("User is deleted");
		}
		
		//valid login -user : persistent -> entity -> dto
			UserDetailsDTO userDetails = mapper.map(user, UserDetailsDTO.class);
			userDetails.setStatus("success");
			return userDetails;
	}
	
	public UserDetailsDTO getUserDetails(Long id) {
		User user = userDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid user id !!!!"));
		
		UserDetailsDTO dto = new UserDetailsDTO(user.getId(), user.getFirstName(), user.getLastName());
		
		return dto;
	}
	
	public ApiResponse signUpUser(SignUp dto) {
		
		if(userDao.existsByEmail(dto.getEmail()))
			throw new ApiException("Email Already Exist !!!");
		
		
		User user = mapper.map(dto, User.class);
		
		user.setRole(UserRole.ROLE_USER);
		
//		user.setRole(UserRole.ROLE_ADMIN);
		
		User persistentUser = userDao.save(user);
		
		return new ApiResponse("New User added with ID=" + persistentUser.getId(), "success");
	}
	
	
	public UpdateUser updateUser(UpdateUser dto) {
		//validate user
		User persistentUser = userDao.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid User id"));
		//user exist 
		//dto updates should apply to the persistent user
		mapper.map(dto, persistentUser);
		
		dto.setId(persistentUser.getId());
		dto.setStatus("success");
		return dto;
	}
	
	
	public ApiResponse deleteUser (Long id) {
		
		User user = userDao.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("Invalid User id"));
		if(user.getRole() == UserRole.ROLE_ADMIN) {
			throw new ApiException("Admin cannot be deleted");
		}
		
		
		if(user.getIsDeleted() == UserDeleteStatus.YES) {
			throw new ApiException("User is already deleted");
		}else {
			user.setIsDeleted(UserDeleteStatus.YES);
		}

		return new ApiResponse("Details of user " + user.getFirstName()+" "
		+ user.getLastName()+ " deleted....", "success");
	}
	
	
	
}
