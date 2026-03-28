package github.jpc.libraryapi.controller.mappers;

import github.jpc.libraryapi.controller.dto.UsuarioDTO;
import github.jpc.libraryapi.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);

}
