package com.desafio.conta.web.rest;

import com.desafio.conta.config.MockConfiguration;
import com.desafio.conta.domain.Usuario;
import com.desafio.conta.service.dto.DadosTransferenciaDTO;
import com.desafio.conta.util.IntTestComum;
import com.desafio.conta.web.rest.util.EntityGenerator;
import com.desafio.conta.web.rest.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(MockConfiguration.class)
public class TranferirResourceIT extends IntTestComum {

    private static final String API_TRANSFERIR = "/api/transferir/";

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void transferir() throws Exception {

        Usuario usuario = EntityGenerator.cadastrarUsuario(em);
        Usuario usuarioDestinatario = EntityGenerator.cadastrarSegundoUsuario(em);

        getMockMvc().perform(put(API_TRANSFERIR)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarTransferenciaDTO(usuarioDestinatario.getId(), usuario.getId()))))
                .andExpect(status().isBadRequest());

        getMockMvc().perform(put(API_TRANSFERIR)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarTransferenciaDTO(usuario.getId(), usuarioDestinatario.getId()))))
                .andExpect(status().isOk());

        getMockMvc().perform(put(API_TRANSFERIR)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarTransferenciaDTO(19999L, usuarioDestinatario.getId()))))
                .andExpect(status().isBadRequest());

        getMockMvc().perform(put(API_TRANSFERIR)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarTransferenciaDTO(usuario.getId(), 19999L))))
                .andExpect(status().isBadRequest());

    }

    public DadosTransferenciaDTO criarTransferenciaDTO(Long idRemetente, Long idDestinatario) {
        return new DadosTransferenciaDTO(idRemetente, idDestinatario, 50D);
    }
}
