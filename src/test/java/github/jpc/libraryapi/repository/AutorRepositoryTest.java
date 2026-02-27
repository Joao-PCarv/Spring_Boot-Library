package github.jpc.libraryapi.repository;

import github.jpc.libraryapi.model.Autor;
import github.jpc.libraryapi.model.GeneroLivro;
import github.jpc.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimanto(LocalDate.of(1951, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTeste() {
        var id = UUID.fromString("2018f827-355b-4c44-9bfc-b3056184374d");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");

            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimanto(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);

        }

    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Total de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("36dcd012-29d8-4c19-9f5f-6d9824556f05");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("c073f250-228b-4d2c-a73a-f21091924bf0");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest() {
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("PT");
        autor.setDataNascimanto(LocalDate.of(1970, 9, 24));

        Livro livro = new Livro();
        livro.setIsbn("978-85-508-0439-8212");
        livro.setPreco(BigDecimal.valueOf(203));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Fantasia");
        livro.setDataPublicacao(LocalDate.of(2020, 10, 10));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("9788212");
        livro2.setPreco(BigDecimal.valueOf(20));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("Ciencia");
        livro2.setDataPublicacao(LocalDate.of(2000, 10, 10));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);


        repository.save(autor);
//        livroRepository.saveAll(autor.getLivros());
    }

}
