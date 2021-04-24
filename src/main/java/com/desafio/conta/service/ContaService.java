package com.desafio.conta.service;

import com.desafio.conta.repository.ContaRepository;
import com.desafio.conta.service.dto.ContaDTO;
import com.desafio.conta.service.dto.DadosContaDTO;
import com.desafio.conta.service.enumeration.TipoEnum;
import com.desafio.conta.service.mapper.ContaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContaService {

    private final ContaRepository contaRepository;

    private final ContaMapper contaMapper;

    public void salvar(ContaDTO contaDTO) {
        contaRepository.save(contaMapper.toEntity(contaDTO));
    }

    @Transactional
    public void adicionarDinheiroConta(Long id, Double valor) {
        contaRepository.adicionarDinheiroConta(id, valor);
    }

    public void obterAtualizarValoresConta(Long idUsuarioDestinario, Long idUsuarioRemetente, Double valor) {
        contaRepository.atualizarValorConta(idUsuarioDestinario, valor, TipoEnum.DESTINATARIO);
        contaRepository.atualizarValorConta(idUsuarioRemetente, valor, TipoEnum.REMETENTE);
    }

    public DadosContaDTO obterContaUsuario(Long id) {
        return contaRepository.obterContaUsuario(id);
    }

}