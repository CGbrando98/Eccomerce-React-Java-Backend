package com.bos.techn.beans;

import java.time.*;  
import java.util.*;

import javax.persistence.*;
import javax.persistence.Id;

import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;

import lombok.*;

@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)

@NoArgsConstructor
@ToString
@Getter
@Setter
// implement userDetails for spring security
public class User implements UserDetails{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_user;
	@Column(unique=true)
	private String username;
	@Column(unique=true)
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@CreatedDate
	private Instant userCreatedDate;
	@LastModifiedDate
	private Instant userLastModifiedDate;
	
	public User(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		// give the authority/role to the role on the accessing user based on the db role
		// if we replace role.name() with Role.ROLE_ADMIN all users are admin
	    authorities.add(new SimpleGrantedAuthority(role.name()));
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
