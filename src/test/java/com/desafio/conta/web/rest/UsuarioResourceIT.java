package com.desafio.conta.web.rest;

import com.desafio.conta.service.dto.UsuarioDTO;
import com.desafio.conta.service.enumeration.TipoUsuarioEnum;
import com.desafio.conta.util.IntTestComum;
import com.desafio.conta.web.rest.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UsuarioResourceIT extends IntTestComum {

    private static final String API_USUARIO = "/api/usuario/";

    @Test
    @Transactional
    public void salvar() throws Exception {

        getMockMvc().perform(post(API_USUARIO)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarUsuarioDTO())))
                .andExpect(status().isOk());

        getMockMvc().perform(post(API_USUARIO)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarUsuarioDTO())))
                .andExpect(status().isBadRequest());

        getMockMvc().perform(post(API_USUARIO)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarUsuarioPjDTO())))
                .andExpect(status().isOk());

    }

    public UsuarioDTO criarUsuarioDTO() {
        return new UsuarioDTO("Ana Souza", TipoUsuarioEnum.COMUM, "963.858.180-80", "heloo@xx.com", "123");
    }

    public UsuarioDTO criarUsuarioPjDTO() {
        return new UsuarioDTO("PicPay", TipoUsuarioEnum.LOJISTA, "28.338.819/0001-82", "pj@xx.com", "123");
    }
}
