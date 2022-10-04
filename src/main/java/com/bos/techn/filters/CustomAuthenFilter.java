package com.bos.techn.filters;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.*;
import com.bos.techn.beans.*;
import com.fasterxml.jackson.databind.*;


public class CustomAuthenFilter extends UsernamePasswordAuthenticationFilter{
	
	
	private AuthenticationManager authManager;
	
	public CustomAuthenFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		System.out.println("attempt login");
		return authManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		System.out.println("success login");
		User user = (User) authentication.getPrincipal();
		// place secret in safe place for prod
		Algorithm algorithm = Algorithm.HMAC256("secret");
		String access_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+ 1*60*1000))
				.withIssuer("auth0")
				.sign(algorithm);
		String refresh_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+60*60*1000*24))
				.withIssuer("auth0")
				.sign(algorithm);
		
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		response.setContentType("application/json");
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
		
	}

}
