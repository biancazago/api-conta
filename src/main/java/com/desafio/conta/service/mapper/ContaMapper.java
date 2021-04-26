package com.desafio.conta.service.mapper;

import com.desafio.conta.domain.Conta;
import com.desafio.conta.service.dto.ContaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContaMapper {

    @Mapping(source = "idUsuario", target = "usuario.id")
    Conta toEntity(ContaDTO var);

}
