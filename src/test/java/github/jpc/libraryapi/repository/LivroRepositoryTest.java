package github.jpc.libraryapi.repository;

import github.jpc.libraryapi.model.Autor;
import github.jpc.libraryapi.model.GeneroLivro;
import github.jpc.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {

        Livro livro = new Livro();
        livro.setIsbn("978-85-508-0439-8");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro");
        livro.setDataPublicacao(LocalDate.of(2020, 10, 10));

        // Recupera o autor existente no banco de dados usando o ID e associa o autor ao livro antes de salvar
        Autor autor = autorRepository
                .findById(UUID.fromString("2018f827-355b-4c44-9bfc-b3056184374d"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest() {

        Livro livro = new Livro();
        livro.setIsbn("978-85-508-0439-8");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro");
        livro.setDataPublicacao(LocalDate.of(2020, 10, 10));

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimanto(LocalDate.of(1951, 1, 31));

        // Associa o autor ao livro e salva o livro, o que também salvará o autor devido à configuração de cascade.all na entidade Livro

        livro.setAutor(autor);

        livroRepository.save(livro);
    }
}