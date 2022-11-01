package com.nt;

import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		/*BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		String password1 = encoder.encode("rani");
		String password2 = encoder.encode("hyd");
		System.out.println(password1);
		System.out.println(password2);*/
		return application.sources(SpringBootSecurity1Application.class);
	}

}
