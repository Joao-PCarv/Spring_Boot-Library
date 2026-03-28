package github.jpc.libraryapi.repository;

import github.jpc.libraryapi.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Client findClientByClientId(String clientId);
}
