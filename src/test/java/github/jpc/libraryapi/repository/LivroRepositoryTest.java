package github.jpc.libraryapi.repository;

import github.jpc.libraryapi.model.Autor;
import github.jpc.libraryapi.model.GeneroLivro;
import github.jpc.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        livro.setTitulo("Outro livro 2");
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
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        // Associa o autor ao livro e salva o livro, o que também salvará o autor devido à configuração de cascade.all na entidade Livro

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("243ea91e-ef1b-4e46-9677-a85c53c6760f");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID id1 = UUID.fromString("2018f827-355b-4c44-9bfc-b3056184374d");
        Autor maria = autorRepository.findById(id1).orElse(null);

        livroParaAtualizar.setAutor(maria);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void delete(){
        UUID id = UUID.fromString("243ea91e-ef1b-4e46-9677-a85c53c6760f");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTeste(){
        UUID id  = UUID.fromString("3a39abfc-10b9-4c5e-9705-6c042721d75a");
        Livro livro = livroRepository.findById(id).orElse(null);

        System.out.println("Livro encontrado: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor do livro: ");
        System.out.println(livro.getAutor().getNome());
    }

}