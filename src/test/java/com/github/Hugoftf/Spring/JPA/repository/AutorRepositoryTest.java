package com.github.Hugoftf.Spring.JPA.repository;

import com.github.Hugoftf.Spring.JPA.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    private AutorRepository autorRepository;


    @Test
    public void salvarTest() {
        Autor autor =  new Autor();
        autor.setNome("Stheffanny");
        autor.setNacionalidade("brasileira");
        autor.setDataNascimento(LocalDate.of(2005, Month.APRIL, 29));

        var autorSalvo = autorRepository.save(autor);

        System.out.println("Autor: " + autorSalvo);

    }

    @Test
    public void atualizarAutorTest(){

      var possivelAutor =  autorRepository.findById(UUID.fromString(
              "ff2be092-d680-4078-bc93-6b3740e245eb"));

        if (possivelAutor.isPresent()){
            System.out.println("Dados do autor:");

            Autor autor = possivelAutor.get();
            System.out.println(autor);

            autor.setDataNascimento(LocalDate.of(1999, Month.DECEMBER, 19));
            autorRepository.save(autor);

            System.out.println("Dados de autor atualizado");
            System.out.println(autor);
        }
        else{
            System.out.println("Autor não encontrado");
        }

    }

    @Test
    public void encontrarTodosAutor(){
        List<Autor> listDeAutor = autorRepository.findAll();
        listDeAutor.forEach(System.out::println);
    }

    @Test
    public void contagemDeAutor(){
        System.out.println("Contagem de autor: " + autorRepository.count());
    }

    @Test
    public void deletarAutorPorID(){
        var id = UUID.fromString(
                "79122f7f-ae90-4351-a87e-673ec35a8fdf");
        autorRepository.deleteById(id);
    }


    @Test
    public void deleteAutor(){

        var possivelAutor = autorRepository.findById(UUID.fromString(
                "79122f7f-ae90-4351-a87e-673ec35a8fdf"));

        if (possivelAutor.isPresent()){
            System.out.println("Dados do autor");
            Autor autorEncontrado = possivelAutor.get();
            System.out.println(autorEncontrado);

            autorRepository.delete(autorEncontrado);
            System.out.println("Autor deletado com sucesso");
        }
        else{
            System.out.println("Autor não encontrado!");
        }

    }
}


