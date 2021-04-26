package com.desafio.conta.web.rest;

import com.desafio.conta.domain.Conta;
import com.desafio.conta.domain.Usuario;
import com.desafio.conta.service.dto.UsuarioDTO;
import com.desafio.conta.service.enumeration.TipoUsuarioEnum;
import com.desafio.conta.util.IntTestComum;
import com.desafio.conta.web.rest.util.EntityGenerator;
import com.desafio.conta.web.rest.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UsuarioResourceIT extends IntTestComum {

    private static final String API_USUARIO = "/api/usuario/";

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void salvar() throws Exception {

        getMockMvc().perform(post(API_USUARIO)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarUsuarioDTO())))
                .andExpect(status().isCreated());

        getMockMvc().perform(post(API_USUARIO)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarUsuarioDTO())))
                .andExpect(status().isBadRequest());

        getMockMvc().perform(post(API_USUARIO)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarUsuarioPjDTO())))
                .andExpect(status().isCreated());

    }

    @Test
    @Transactional
    public void obterId() throws Exception {

        Usuario usuario = EntityGenerator.cadastrarUsuario(em);

        getMockMvc().perform(get(API_USUARIO + usuario.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        getMockMvc().perform(get(API_USUARIO + "19999999")
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());

    }

    public UsuarioDTO criarUsuarioDTO() {
        return new UsuarioDTO("Ana Souza", TipoUsuarioEnum.COMUM, "963.858.180-80", "heloo@xx.com", "123");
    }

    public UsuarioDTO criarUsuarioPjDTO() {
        return new UsuarioDTO("PicPay", TipoUsuarioEnum.LOJISTA, "28.338.819/0001-82", "pj@xx.com", "123");
    }
}
