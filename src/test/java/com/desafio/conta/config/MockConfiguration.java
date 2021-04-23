package com.desafio.conta.config;

import com.desafio.conta.service.dto.MessagemDTO;
import com.desafio.conta.service.feign.MockAutorizadorFeignClient;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MockConfiguration {

    @MockBean
    private MockAutorizadorFeignClient mockAutorizadorFeignClient;


    @PostConstruct
    private void mockServices() {
        MessagemDTO mensagemAutorizado = new MessagemDTO();
        mensagemAutorizado.setMessage("Autorizado");
        Mockito.when(mockAutorizadorFeignClient.autorizadorTrasferencia()).thenReturn(mensagemAutorizado);

        MessagemDTO mensagemConcluido = new MessagemDTO();
        mensagemConcluido.setMessage("Enviado");
        Mockito.when(mockAutorizadorFeignClient.verificarConclusaoTransferencia()).thenReturn(mensagemConcluido);

    }

}
