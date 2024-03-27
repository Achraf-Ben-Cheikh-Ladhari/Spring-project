package com.ladharitech.ladhari;

import com.ladharitech.ladhari.entity.user.Role;
import com.ladharitech.ladhari.entity.user.User;
import com.ladharitech.ladhari.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LadhariApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepo;
	public static void main(String[] args) {
		SpringApplication.run(LadhariApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepo.findByRole(Role.ADMIN);
		User superAdminAccount=userRepo.findByRole(Role.SUPER_ADMIN);
		createAdminAndSuperAdmin(adminAccount,superAdminAccount);
	}

	private void createAdminAndSuperAdmin(User admin,User superAdmin){
		//admin
		User adminAccount = new User();
		adminAccount.setEmail("admin@admin.com");
		adminAccount.setRole(Role.ADMIN);
		adminAccount.setFirstname("admin");
		adminAccount.setLastname("admin");
		adminAccount.setEnabled(true);
		adminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
		userRepo.save(adminAccount);
		//super admin
		adminAccount.setEmail("superadmin@superadmin.com");
		adminAccount.setRole(Role.SUPER_ADMIN);
		adminAccount.setFirstname("super admin");
		adminAccount.setLastname("super admin");
		adminAccount.setEnabled(true);
		adminAccount.setPassword(new BCryptPasswordEncoder().encode("superAdmin"));
		userRepo.save(adminAccount);
	}
}
