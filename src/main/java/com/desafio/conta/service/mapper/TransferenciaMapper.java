package com.desafio.conta.service.mapper;

import com.desafio.conta.domain.Transferencia;
import com.desafio.conta.service.dto.TransferenciaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

    Transferencia toEntity(TransferenciaDTO var);

    TransferenciaDTO toDto(Transferencia var);
}
