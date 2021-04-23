package com.desafio.conta.mapper;

import com.desafio.conta.domain.Conta;
import com.desafio.conta.dto.ContaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    @Mapping(source = "idUsuario", target = "usuario.id")
    Conta toEntity(ContaDTO var);

    @Mapping(source = "usuario.id", target = "idUsuario")
    ContaDTO toDto(Conta var);
}
