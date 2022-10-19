package com.bos.techn;

import org.springframework.beans.factory.annotation.*;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.crypto.bcrypt.*;

import com.auth0.jwt.algorithms.*;
import com.cloudinary.*;
import com.cloudinary.utils.*;
import com.auth0.jwt.algorithms.*;

@SpringBootApplication
@EnableJpaAuditing
public class TechnApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnApplication.class, args);
	}
	
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
