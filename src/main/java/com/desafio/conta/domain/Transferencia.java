package com.desafio.conta.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TRANSFERENCIA")
@Getter
@Setter
public class Transferencia implements Serializable {

    @Id
    @Column(name = "ID_TRANSFERENCIA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALOR", nullable = false)
    private Double valor;

    @Column(name = "ID_USUARIO_REMETENTE")
    private Long idUsuarioRemetente;

    @Column(name = "ID_USUARIO_DESTINATARIO")
    private Long idUsuarioDestinatario;

    @Column(name = "DATA_HORA")
    private LocalDateTime data;
}
