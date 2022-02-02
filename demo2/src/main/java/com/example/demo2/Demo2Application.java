package com.example.demo2;

import com.example.demo2.domain.Role;
import com.example.demo2.domain.User;
import com.example.demo2.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

//	@Bean
//	CommandLineRunner run(UserService userService){
//		return args -> {
//			userService.saveRole(new Role(null,"ROLE_USER"));
//			userService.saveRole(new Role(null,"ROLE_MANAGER"));
//			userService.saveRole(new Role(null,"ROLE_ADMIN"));
//			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
//
//
//			userService.saveUser(new User(null,"John Cena","john","1234",new ArrayList<>()));
//			userService.saveUser(new User(null,"Will Smith","Will","1234",new ArrayList<>()));
//			userService.saveUser(new User(null,"arjun","arj","1234",new ArrayList<>()));
//			userService.saveUser(new User(null,"parth","par","1234",new ArrayList<>()));
//
//			userService.addRoleToUser("john","ROLE_USER");
//			userService.addRoleToUser("john","ROLE_MANAGER");
//			userService.addRoleToUser("arj","ROLE_SUPER_ADMIN");
//			userService.addRoleToUser("arj","ROLE_ADMIN");
//			userService.addRoleToUser("par","ROLE_ADMIN");
//			userService.addRoleToUser("will","ROLE_MANAGER");
//		};
//	}
}
