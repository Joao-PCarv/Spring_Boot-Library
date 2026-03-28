package github.jpc.libraryapi.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "Login é obrigatório")
        String login,
        @NotBlank(message = "Senha é obrigatória")
        String senha,
        @Email(message = "Email deve ser válido")
        @NotBlank(message = "Email é obrigatório")
        String email,
        List<String> roles) {
}
