//package com.bos.techn;
//
//import java.io.*;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.security.authentication.*;
//import org.springframework.security.core.context.*;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.security.web.authentication.*;
//import org.springframework.stereotype.*;
//import org.springframework.web.filter.*;
//
//import com.bos.techn.beans.*;
//import com.bos.techn.services.*;
//
//@Component
//public class JWTRequestFilter extends OncePerRequestFilter{
//	
//	@Autowired
//	private UserServices userServices;
//	
//	@Autowired
//	private JWTUtil jwtTokenUtil;
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		String authorizationHeader = request.getHeader("Authorization");
//		String username = null;
//		String jwt = null;
//		
//		if (authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")) {
//			jwt = authorizationHeader.substring(7);
//			username = jwtTokenUtil.extractUsername(jwt);
//		}
//		
//		if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null) {
//			UserDetails userDetails = userServices.loadUserByUsername(username);
//			if (jwtTokenUtil.validateToken(jwt, userDetails)) {
//				
//				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new 
//						UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource()
//						.buildDetails(request));
//				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//						
//			}
//		} 
//		filterChain.doFilter(request, response);
//		
//	}
//
//}
