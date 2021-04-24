package com.desafio.conta.web.rest.util;

import com.desafio.conta.domain.Conta;
import com.desafio.conta.domain.Usuario;
import com.desafio.conta.service.enumeration.TipoUsuarioEnum;

import javax.persistence.EntityManager;

public final class EntityGenerator {

    private EntityGenerator() {
    }

    public static Usuario cadastrarUsuario(EntityManager em) {
        Usuario usuario = new Usuario();
        usuario.setCpfCnpj("77241396014");
        usuario.setEmail("login@xxx.com");
        usuario.setNome("login");
        usuario.setSenha("123");
        usuario.setTipoUsuario(TipoUsuarioEnum.COMUM);


        em.persist(usuario);
        em.flush();

        Conta conta = new Conta();
        conta.setSaldo(1000D);
        conta.setUsuario(usuario);
        em.persist(conta);
        em.flush();

        return usuario;
    }

    public static Usuario cadastrarSegundoUsuario(EntityManager em) {
        Usuario usuario = new Usuario();
        usuario.setCpfCnpj("87476683005");
        usuario.setEmail("login2@xxx.com");
        usuario.setNome("login2");
        usuario.setSenha("123");
        usuario.setTipoUsuario(TipoUsuarioEnum.COMUM);

        em.persist(usuario);
        em.flush();

        Conta conta = new Conta();
        conta.setSaldo(0D);
        conta.setUsuario(usuario);
        em.persist(conta);
        em.flush();

        return usuario;
    }

    public static Conta cadastraConta(EntityManager em) {
        Usuario usuario = new Usuario();
        usuario.setCpfCnpj("87476683005");
        usuario.setEmail("login2@xxx.com");
        usuario.setNome("login2");
        usuario.setSenha("123");
        usuario.setTipoUsuario(TipoUsuarioEnum.COMUM);

        em.persist(usuario);
        em.flush();

        Conta conta = new Conta();
        conta.setSaldo(1000D);
        conta.setUsuario(usuario);
        em.persist(conta);
        em.flush();

        return conta;
    }
}
