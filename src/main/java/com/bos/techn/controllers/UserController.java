package com.bos.techn.controllers;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*; 
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.*;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.*;
import com.bos.techn.*;
import com.bos.techn.beans.*;
import com.bos.techn.beans.User;
import com.bos.techn.exceptions.*;
import com.bos.techn.services.*;
import com.fasterxml.jackson.core.exc.*;
import com.fasterxml.jackson.databind.*;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
//	@Autowired
//	private JWTUtil jwtTokenUtil;

	@PostMapping("/users")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		userServices.saveUser(user);
		return new ResponseEntity("Saved", HttpStatus.OK);
	}
	
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
	
	@GetMapping("/users/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response ) throws StreamWriteException, DatabindException, IOException { 
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
			String refresh_token = authorizationHeader.substring("Bearer ".length());
			Algorithm algorithm = Algorithm.HMAC256("secret");
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(refresh_token);
			String username = decodedJWT.getSubject();
			UserDetails user = userServices.loadUserByUsername(username);
			
			System.out.println(user);
			
			String access_token = JWT.create()
					.withSubject(user.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis()+ 1*60*1000))
					.withIssuer("auth0")
					.sign(algorithm);
			
			Map<String, String> tokens = new HashMap<>();
			tokens.put("access_token", access_token);
			tokens.put("refresh_token", refresh_token);
			response.setContentType("application/json");
			new ObjectMapper().writeValue(response.getOutputStream(), tokens);

			} catch (Exception e) {
				System.err.println("Error Logging in!");
				response.setHeader("Error", e.getMessage());
				response.setStatus(403);
				Map<String, String> error = new HashMap<>();
				error.put("error_msg", e.getMessage());
				response.setContentType("application/json");
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else {
			throw new RuntimeException("Refresh Token Missing");
		}

	}
}
