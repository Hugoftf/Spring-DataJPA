package com.github.Hugoftf.Spring.JPA.repository;

import com.github.Hugoftf.Spring.JPA.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
