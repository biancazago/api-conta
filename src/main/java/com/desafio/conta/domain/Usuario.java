package com.desafio.conta.domain;


import com.desafio.conta.service.enumeration.TipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TB_USUARIO")
@Getter
@Setter
public class Usuario implements Serializable {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 200)
    private String nome;

    @Column(name = "TIPO_USUARIO", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoUsuarioEnum tipoUsuario;

    @Column(name = "CPF_CNPJ", nullable = false, unique = true, length = 14)
    private String cpfCnpj;

    @Column(name = "EMAIL", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "SENHA", nullable = false, length = 60)
    private String senha;

}
