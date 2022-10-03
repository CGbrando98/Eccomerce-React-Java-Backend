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
	
	public void saveUser(User user) {
		try {
			// use spring security for hashing
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRole("ADMIN");
			userDao.save(user);
		} catch (Exception e) {
			System.out.println(e);
			System.err.println("Error in saving");
		}
	}


	public User getUser(int id) throws UserNotFoundException {
		Optional<User> optional = userDao.findById(id);
		// lambda function 
		Supplier<UserNotFoundException> exceptionSupplier = () -> new 
				UserNotFoundException("User not found for id: "+id);
		return optional.orElseThrow(exceptionSupplier);
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
		} else {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(user.getRole()));
			
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getPassword(), authorities);
		
		}
			
	}

}
