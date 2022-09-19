package com.bos.techn.services;

import java.security.*;
import java.util.*; 
import java.util.function.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bos.techn.beans.*;
import com.bos.techn.daos.*;
import com.bos.techn.exceptions.*;

@Component
public class UserServicesImpl implements UserServices {
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public void saveUser(User user) {
		try {
			// use spring security for hashing
			BCryptPasswordEncoder bCryptPasswordEncoder =  new 
					BCryptPasswordEncoder(10, new SecureRandom());
			String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
			
			user.setPassword(encodedPassword);
			user.setAdmin(false);
			userDao.save(user);
		} catch (Exception e) {
			System.out.println(e);
			System.err.println("Error in saving");
		}
	}

	@Override
	public User getUser(int id) throws UserNotFoundException {
		Optional<User> optional = userDao.findById(id);
		// lambda function 
		Supplier<UserNotFoundException> exceptionSupplier = () -> new 
				UserNotFoundException("User not found for id: "+id);
		return optional.orElseThrow(exceptionSupplier);
	}

	@Override
	public void deleteUser(int id) throws UserNotFoundException {
		try {
			userDao.deleteById(id);
		}
		catch (Exception e) {
			System.out.println(e);
			throw new UserNotFoundException("Could not find User to delete with "
					+ "id: "+ id);
		}
	}
}
