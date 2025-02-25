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
		var context = SpringApplication.run(SpringJpaApplication.class, args);
		var autoRepositoryBean = context.getBean(AutorRepository.class);
		salvar(autoRepositoryBean);
	}

	public static void salvar(AutorRepository autorRepository){
		Autor autor =  new Autor();
		autor.setNome("Hugo");
		autor.setNacionalidade("brasileiro");
		autor.setDataNascimento(LocalDate.of(1998, Month.JUNE, 16));

		var autorSalvo = autorRepository.save(autor);

		System.out.println("Autor: " + autorSalvo);
	}
}
