package com.desafio.conta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO implements Serializable {

    private Long id;

    private Double valor;

    private Long idUsuario;

}
