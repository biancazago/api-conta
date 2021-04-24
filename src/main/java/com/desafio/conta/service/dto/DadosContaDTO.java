package com.desafio.conta.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosContaDTO implements Serializable {

    private Long id;

    private Long idUsuario;

    private String nomeUsuario;

    private Double valor;

}
