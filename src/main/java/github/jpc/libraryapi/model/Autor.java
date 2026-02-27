package github.jpc.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="autor", schema = "public") // Especifica o nome da tabela no banco de dados
@Getter // Gera os métodos getters para os atributos da classe
@Setter
@ToString
public class Autor {

    //Mapeamento dos atributos da classe para as colunas da tabela no banco de dados do autor
    // se a anotação @Table, todos os atributos da classe serão mapeados para as colunas da tabela

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimanto;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL) // Especifica o relacionamento um-para-muitos entre Autor e Livro, onde "autor" é o nome do atributo na classe Livro que referencia o Autor
    private List<Livro> livros;

}
