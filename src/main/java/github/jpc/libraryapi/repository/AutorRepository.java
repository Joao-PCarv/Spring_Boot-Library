package github.jpc.libraryapi.repository;


import github.jpc.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// Não é necessário colocar annotation @Repository, pois a interface JpaRepository já é reconhecida como um componente de repositório pelo Spring Data JPA
public interface AutorRepository extends JpaRepository<Autor, UUID> {
}
