package com.desafio.conta.service.dto;

import com.desafio.conta.service.enumeration.TipoUsuarioEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListDTO implements Serializable {

    private Long id;

    private String nome;

    private TipoUsuarioEnum tipoUsuario;

    private String cpfCnpj;

    private String email;

}
