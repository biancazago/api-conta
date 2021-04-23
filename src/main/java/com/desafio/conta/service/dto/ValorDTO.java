package com.desafio.conta.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ValorDTO implements Serializable {

    @NotNull(message = "Valor é campo obrigatório")
    private Double valor;

}
