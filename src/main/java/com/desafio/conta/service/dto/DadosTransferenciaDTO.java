package com.desafio.conta.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosTransferenciaDTO implements Serializable {

    private Long idUsuario;

    private String senha;

    private Double valor;

}
