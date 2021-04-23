package com.desafio.conta.service.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AutorizadorEnum {
    AUTORIZADO("Autorizado"), NÃO_AUTORIZADO("Não Autorizado"), ENVIADO("Enviado");

    private final String descricao;
}
