package com.github.Hugoftf.Spring.JPA.repository;

import com.github.Hugoftf.Spring.JPA.model.Autor;
import com.github.Hugoftf.Spring.JPA.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query method

    // Encontrar livros por autor
    List<Livro> findByAutor(Autor autor);

    // Encontrar livros por titulo
    List<Livro> findByTitulo(String titulo);

    // Encontrar livros por titulo ou ISBN
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    // Encontrar livros entre datas de publicações
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);



}
