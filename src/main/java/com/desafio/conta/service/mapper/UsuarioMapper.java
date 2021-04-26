package com.desafio.conta.service.mapper;

import com.desafio.conta.domain.Usuario;
import com.desafio.conta.service.dto.UsuarioDTO;
import com.desafio.conta.service.dto.UsuarioListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO var);

    UsuarioListDTO toDtoList(Usuario var);
}
