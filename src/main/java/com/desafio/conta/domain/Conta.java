package com.desafio.conta.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TB_CONTA")
@Getter
@Setter
public class Conta implements Serializable {

    @Id
    @Column(name = "ID_CONTA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SALDO", nullable = false)
    private Double saldo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USUARIO")
    private Usuario usuario;

}
