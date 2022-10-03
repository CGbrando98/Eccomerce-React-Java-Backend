package com.bos.techn.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.bos.techn.beans.*;
import com.bos.techn.exceptions.*;
import com.bos.techn.services.*;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserServices userServices;

	@PostMapping("/users")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		userServices.saveUser(user);
		return new ResponseEntity<>("User added", HttpStatus.OK);
	}
	
//	@PostMapping("/users/login")
//	public ResponseEntity<User> loginUser(@RequestBody User user) throws UserNotFoundException {
//		User existingUser = userServices.authUser(user);
//		return new ResponseEntity<User>("hi", HttpStatus.OK);
//	}
	
	@GetMapping("/users/{userid}")
	public ResponseEntity<User> searchUser(@PathVariable int userid) throws UserNotFoundException{
		User user = userServices.getUser(userid);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{userid}")
	public ResponseEntity<String> removeUser( @PathVariable int userid) throws UserNotFoundException{
		userServices.deleteUser(userid);
		return new ResponseEntity<>("User deleted", HttpStatus.OK);
	}
}
