package com.bos.techn.filters;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import com.auth0.jwt.*;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.*;
import com.auth0.jwt.interfaces.*;
import com.bos.techn.daos.*;
import com.bos.techn.services.*;
import com.fasterxml.jackson.databind.*;

@Component
public class CustomAuthorFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserServices userServices;
	
	@Value("${spring.token.secret}")
	String secret;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// if the user is just logging in we don't need to do any authorization 
		if(request.getServletPath().equals("/login") || request.getServletPath().equals("/users/token/refresh")) {
			filterChain.doFilter(request, response);
			System.out.println("login authorization");
		} else {
			String authorizationHeader = request.getHeader("Authorization");
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				try {
				String token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256(secret);
				JWTVerifier verifier = JWT.require(algorithm).withIssuer("auth0").build();
				DecodedJWT decodedJWT = verifier.verify(token);
				int id = Integer.valueOf(decodedJWT.getSubject());
				String username = userServices.getUser(id).getUsername();
				
				UserDetails userDetails = userServices.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken authToken = new 
						UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
				} catch (Exception e) {
					System.err.println("Error Logging in!");
					response.setHeader("Error", e.getMessage());
					response.setStatus(403);
					Map<String, String> error = new HashMap<>();
					error.put("error_msg", e.getMessage());
					response.setContentType("application/json");
					new ObjectMapper().writeValue(response.getOutputStream(), error);
				}
			}
			filterChain.doFilter(request, response);
		}
		
	}

}
