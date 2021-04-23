package com.desafio.conta.service.mapper;

import com.desafio.conta.domain.Usuario;
import com.desafio.conta.service.dto.UsuarioDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO var);

    UsuarioDTO toDto(Usuario var);
}