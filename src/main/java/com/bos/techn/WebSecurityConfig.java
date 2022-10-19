package com.bos.techn;

import javax.annotation.*;
import javax.servlet.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.*;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.algorithms.*;
import com.bos.techn.beans.*;
import com.bos.techn.filters.*;
import com.bos.techn.services.*;

@Configuration
@EnableWebSecurity 
@CrossOrigin(origins="http://localhost:3000")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { 
	
	@Value("${spring.token.secret}")
	String secret;
	
	@Bean
	public AuthenticationManager authManagerBean() throws Exception {
		return super.authenticationManagerBean(); 
	} 
	
	@Bean 
	public Algorithm algorithmBean() throws Exception {
		return Algorithm.HMAC256(secret); 
	} 
 
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired 
	private UserServices userServices;
	
	@Autowired
	private CustomAuthorFilter customAuthorFilter;
	
	@Autowired 
	private CustomCorsFilterConfig customCorsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userServices).passwordEncoder(bCryptPasswordEncoder);
    }

	
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	  http.addFilterBefore(customCorsFilter,  UsernamePasswordAuthenticationFilter.class);
      http.csrf().disable();
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      http.authorizeRequests()
      .antMatchers(HttpMethod.POST, "/users").permitAll() 
      .antMatchers(HttpMethod.DELETE, "/users/*").hasRole("ADMIN")
      .antMatchers(HttpMethod.DELETE, "/products/*").hasRole("ADMIN")
      .antMatchers(HttpMethod.PUT, "/products/*").hasRole("ADMIN")
      .antMatchers(HttpMethod.GET, "/orders").hasRole("ADMIN")
      .antMatchers("/products/**", "/login", "/users/token/refresh", "/config/paypal", "/upload").permitAll()
      .antMatchers("/users").hasRole("ADMIN")
      .antMatchers("/orders/**", "/users/*").hasAnyRole("ADMIN", "USER")
      .anyRequest()
      .authenticated()
      .and().formLogin()
      .loginPage("http://localhost:3000/login").permitAll()
      .loginProcessingUrl("/login");

      
      http.addFilter(new CustomAuthenFilter(authManagerBean(),algorithmBean() ));
      http.addFilterBefore(customAuthorFilter, UsernamePasswordAuthenticationFilter.class);
  }
}