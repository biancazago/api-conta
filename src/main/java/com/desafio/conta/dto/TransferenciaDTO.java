package com.desafio.conta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransferenciaDTO implements Serializable {

    @NotNull(message = "Id Usuario Rementente é preenchimento obrigatorio")
    private Long idUsuarioRemetente;

    @NotNull(message = "Id Usuario Destinatário é preenchimento obrigatorio")
    private Long idUsuarioDestinatario;

    @NotNull(message = "Valor preenchimento obrigatorio")
    private Double valor;

}
