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
		user1.setSubdivision("2");
		authenticationService.register(user1);
		PDProcess pdProcess=new PDProcess();
		pdProcess.setName("pd1");
		PDProcessAction action=new PDProcessAction();
		action.setName("удаление");
		pdProcess.setPdProcessActions(List.of(action));
		PDProcessType pdProcessType=new PDProcessType();
		pdProcessType.setName("Локальная сеть");
		pdProcess.setPdProcessType(pdProcessType);
		Employee employee=new Employee();
		employee.setName("Vasya");
		employee.setEmail("Vasya@mail.com");
		pdProcess.setEmployee(employee);
		ICOPD icopd=new ICOPD();
		icopd.setName("icopd1");
		Subdivision s=new Subdivision();
		s.setName("1");
		icopd.setSubdivision(s);
		pdProcess.setIcopd(icopd);
		PDDocument pdDocument=new PDDocument();
		pdDocument.setName("pasport");
		pdProcess.setPdDocument(pdDocument);
		PDStorage pdStorage =new PDStorage();
		pdStorage.setName("f1");
		pdProcess.setPdStorage(pdStorage);
		PDType pdType =new PDType();
		pdType.setName("ФИО");
		pdProcess.setPdType(pdType);
		pdProcess.setThirdPeople(true);
		PDTarget pdTarget = new PDTarget("оказание услуг");
		pdProcess.setPdTargets(List.of(pdTarget));
		PDRegDoc pdRegDoc = new PDRegDoc();
		pdRegDoc.setName("Политика");
		pdProcess.setPdRegDoc(pdRegDoc);
		pdProcessService.createIfNotExists(pdProcess);
	}
}
