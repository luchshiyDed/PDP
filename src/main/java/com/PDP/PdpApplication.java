package com.PDP;

import com.PDP.model.*;
import com.PDP.security.JWTService;
import com.PDP.security.authentication.AuthenticationService;
import com.PDP.security.config.SecurityConfig;
import com.PDP.security.user.UserEntity;
import com.PDP.security.user.UserRole;
import com.PDP.service.PDProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PdpApplication implements CommandLineRunner {
	public static void main(String[] args) {

		SpringApplication.run(PdpApplication.class, args);
	}
	@Autowired
	PDProcessService pdProcessService;
	@Autowired
	AuthenticationService authenticationService;
	@Override
	public void run(String... args) throws Exception {

		UserEntity user=new UserEntity();
		user.setPassword("admin");
		user.setRole(UserRole.ADMIN);
		user.setName("admin");
		user.setAllSubdivisions(Boolean.TRUE);
		authenticationService.register(user);
		UserEntity user1=new UserEntity();
		user1.setPassword("1");
		user1.setRole(UserRole.VIEWER);
		user1.setName("1");
		user1.setSubdivision("1");
		UserEntity user2=new UserEntity();
		user2.setPassword("2");
		user2.setRole(UserRole.CREATOR);
		user2.setName("2");
		user2.setSubdivision("2");
		authenticationService.register(user1);
		authenticationService.register(user2);

	}
}
