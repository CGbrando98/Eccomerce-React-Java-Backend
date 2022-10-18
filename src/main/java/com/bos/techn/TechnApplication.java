package com.bos.techn;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;
import org.springframework.security.crypto.bcrypt.*;


import com.cloudinary.*;
import com.cloudinary.utils.*;

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
    
//    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
//    		//hide this info
//    "cloud_name", "da6su05rx",
//    "api_key", "658295152147355",
//    "api_secret", "uESbpI7kfembW62YMJtirgH4BfE",
//    "secure", true));
  
}
