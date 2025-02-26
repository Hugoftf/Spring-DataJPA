package com.github.Hugoftf.Spring.JPA;

import com.github.Hugoftf.Spring.JPA.model.Autor;
import com.github.Hugoftf.Spring.JPA.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);

	}

}
