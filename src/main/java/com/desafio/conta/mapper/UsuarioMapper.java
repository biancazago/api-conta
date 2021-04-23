package com.desafio.conta.mapper;

import com.desafio.conta.domain.Usuario;
import com.desafio.conta.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO var);

    UsuarioDTO toDto(Usuario var);
}
