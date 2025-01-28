package com.Blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.Blog.config.AppConstant;
import com.Blog.enetites.Role;
import com.Blog.repositers.RoleRepo;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(this.passwordEncoder.encode("asdfghjkl"));
		
		try {
			
			Role role=new Role();
			
			role.setId(AppConstant.ADMMINE_USER);
			role.setName("ADMINE_USER");
			
             Role role1=new Role();
			
			role1.setId(AppConstant.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			List<Role> roles = List.of(role,role1);
			
			List<Role> all = roleRepo.saveAll(roles);
			
			all.forEach(r->{
				System.out.println(r.getName());
			});
			
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		
	}

}
