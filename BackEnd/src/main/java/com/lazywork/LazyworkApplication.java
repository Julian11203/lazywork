package com.lazywork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LazyworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(LazyworkApplication.class, args);
	}

	@Configuration
	@EnableWebMvc
	public class CorsConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**")
					.allowedOrigins("http://127.0.0.1:5500") // Reemplaza con la URL de tu aplicación frontend
					.allowedMethods("GET", "POST", "PUT", "DELETE")
					.allowedHeaders("*");
		}
	}

}