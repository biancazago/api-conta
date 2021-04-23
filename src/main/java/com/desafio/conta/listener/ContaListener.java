package com.desafio.conta.listener;

import com.desafio.conta.service.ContaService;
import com.desafio.conta.service.dto.ContaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class ContaListener {

    private final ContaService contaService;

    @EventListener
    public void criarConta(ContaDTO contaDTO) {
        contaService.salvar(contaDTO);
    }

}
