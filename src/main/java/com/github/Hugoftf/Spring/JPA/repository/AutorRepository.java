package com.github.Hugoftf.Spring.JPA.repository;

import com.github.Hugoftf.Spring.JPA.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
