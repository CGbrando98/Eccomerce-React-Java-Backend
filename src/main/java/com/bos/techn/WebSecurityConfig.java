package com.bos.techn;

import javax.annotation.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.*;

import com.bos.techn.beans.*;
import com.bos.techn.services.*;

@Configuration
@EnableWebSecurity 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	private UserServices userServices;
	
	@Autowired
	private JWTRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userServices).passwordEncoder(bCryptPasswordEncoder);
    	
    	// create in memory test user
//    	auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//    	.withUser("foo")
//    	.password("foo")
//    	.roles("ADMIN");
    }

	
   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
      .csrf().disable();
      http.authorizeRequests()
      .antMatchers("/carts/**", "/users/*").hasRole("ADMIN")
      .antMatchers(HttpMethod.POST, "/users").permitAll() 
      .antMatchers("/products/**").permitAll()
      .anyRequest()
      .authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//      .and().formLogin();
//    .loginPage("http://localhost:3000/users/login").permitAll();
      
      http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
  }
  
}