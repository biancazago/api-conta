package com.desafio.conta.web.rest;

import com.desafio.conta.domain.Conta;
import com.desafio.conta.service.dto.ValorDTO;
import com.desafio.conta.util.IntTestComum;
import com.desafio.conta.web.rest.util.EntityGenerator;
import com.desafio.conta.web.rest.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ContaResourceIT extends IntTestComum {

    private static final String API_CONTA = "/api/conta/";

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    public void salvar() throws Exception {

        Conta conta = EntityGenerator.cadastraConta(em);

        getMockMvc().perform(patch(API_CONTA + conta.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(criarValorDTO())))
                .andExpect(status().isOk());

    }

    public ValorDTO criarValorDTO() {
        return new ValorDTO(0D);
    }
}
