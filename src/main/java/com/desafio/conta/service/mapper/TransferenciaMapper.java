package com.desafio.conta.service.mapper;

import com.desafio.conta.domain.Transferencia;
import com.desafio.conta.service.dto.DadosTransferenciaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

    Transferencia toEntity(DadosTransferenciaDTO var);
}
