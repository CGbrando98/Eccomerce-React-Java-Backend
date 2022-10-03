package com.bos.techn.services;

import org.springframework.security.core.userdetails.*; 
import com.bos.techn.beans.User;
import com.bos.techn.exceptions.*;

public interface UserServices extends UserDetailsService{
	void saveUser(User user);
	User getUser(int id) throws UserNotFoundException;
	void deleteUser(int id) throws UserNotFoundException;
}
