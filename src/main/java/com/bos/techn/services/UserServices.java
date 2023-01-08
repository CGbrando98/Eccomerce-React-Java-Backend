package com.bos.techn.services;

import java.util.*;

import org.springframework.security.core.userdetails.*; 
import com.bos.techn.beans.User;
import com.bos.techn.exceptions.*;

public interface UserServices extends UserDetailsService{
	void saveUser(User user) throws SavingDataException;
	User getUser(UUID userid) throws UserNotFoundException;
	void deleteUser(UUID userid) throws UserNotFoundException;
	User updateUser(User user, UUID userid) throws UserNotFoundException;
	List<User> getUsers();
}
