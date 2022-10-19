package com.bos.techn.services;

import java.security.*; 
import java.util.*; 
import java.util.function.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;

import com.bos.techn.*;
import com.bos.techn.beans.*;
import com.bos.techn.beans.User;
import com.bos.techn.daos.*;
import com.bos.techn.exceptions.*;

@Component
public class UserServicesImpl implements UserServices, UserDetailsService {
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void saveUser(User user) throws SavingDataException {
		try {
			// use spring security for hashing
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRole(Role.ROLE_USER);
			userDao.save(user);
		} catch (Exception e) {
			throw new SavingDataException("Error in Saving User Data");
		}
	}


	public User getUser(int id) throws UserNotFoundException {
		Optional<User> optional = userDao.findById(id);
		// lambda function 
		Supplier<UserNotFoundException> exceptionSupplier = () -> new 
				UserNotFoundException("User not found for id: "+id);
		return optional.orElseThrow(exceptionSupplier);
	}
	

	@Override
	public List<User> getUsers() {
		return userDao.findAll();
	}
	
	@Override
	public User updateUser(User newUser, int id) throws UserNotFoundException {
		try {
			Optional<User> optional = userDao.findById(id);
			Supplier<UserNotFoundException> exceptionSupplier = () -> new 
					UserNotFoundException("User not found for id");
			newUser.setId_user(id);
			System.out.println(optional.get());
			System.out.println(newUser);
			
			if (newUser.getRole()== null) {
				newUser.setRole(optional.get().getRole());
			} 
			
			if (newUser.getEmail() == "") {
				newUser.setEmail(optional.get().getEmail());
			}

			if (newUser.getUsername() == "") {
				newUser.setUsername(optional.get().getUsername());
			}

			if (newUser.getPassword() == "" || newUser.getPassword() == null) {
				newUser.setPassword(optional.get().getPassword());
			} else {
				newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
			}
			return userDao.save(newUser);
			} catch (Exception e) {
				throw new UserNotFoundException("Could not find User to update with "
						+ "id: "+ id);
			}
		}

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		} 
		return user;
	}


}
