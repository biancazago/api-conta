package com.desafio.conta.service;

import com.desafio.conta.domain.Transferencia;
import com.desafio.conta.repository.TransferenciaRepository;
import com.desafio.conta.service.dto.DadosTransferenciaDTO;
import com.desafio.conta.service.dto.MensagemDTO;
import com.desafio.conta.service.dto.TransferenciaDTO;
import com.desafio.conta.service.enumeration.AutorizadorEnum;
import com.desafio.conta.service.feign.MockAutorizadorFeignClient;
import com.desafio.conta.service.mapper.TransferenciaMapper;
import com.desafio.conta.util.AutorizadorException;
import com.desafio.conta.util.ConstantsUtil;
import com.desafio.conta.util.RegraNegocioException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TransferirService {

    private final UsuarioService usuarioService;

    private final ContaService contaService;

    private final TransferenciaMapper transferenciaMapper;

    private final TransferenciaRepository transferenciaRepository;

    private final MockAutorizadorFeignClient mockAutorizadorFeignClient;

    @Transactional(rollbackFor = ExecutionException.class, timeout = 5)
    public TransferenciaDTO transferir(TransferenciaDTO transferenciaDTO) {
        if (validarTransferencia(transferenciaDTO).equals(AutorizadorEnum.AUTORIZADO.getDescricao())) {
            contaService.obterAtualizarValoresConta(transferenciaDTO.getIdUsuarioDestinatario(), transferenciaDTO.getIdUsuarioRemetente(), transferenciaDTO.getValor());
            return salvarTransferencia(transferenciaDTO);
        }
        throw new AutorizadorException(ConstantsUtil.TRANSFERENCIA_NAO_AUTORIZADA);

    }

    private void validarDadosUsuarioTransferencia(TransferenciaDTO transferenciaDTO) {
        DadosTransferenciaDTO dados = usuarioService.obterDadosTransferencia(transferenciaDTO);
        validarRemetente(dados.getIdUsuarioRemetente());
        validarDestinatario(dados.getIdUsuarioDestinatario());
        validarValorEnviado(transferenciaDTO.getValor(), dados.getSaldoUsuarioRemetente());
    }

    private void validarRemetente(Long idRemetente) {
        if (idRemetente == null) {
            throw new RegraNegocioException(ConstantsUtil.REMETENTE_NAO_ENCONTRADO);
        }
    }

    private void validarDestinatario(Long idDestinatario) {
        if(idDestinatario == null) {
            throw new RegraNegocioException(ConstantsUtil.DESTINATARIO_NAO_ENCONTRADO);
        }
    }

    private void validarValorEnviado(Double valorEnviado, Double valorAtualConta) {
        if (valorEnviado > valorAtualConta) {
            throw new RegraNegocioException(ConstantsUtil.USUARIO_NAO_POSSUI_SALDO);
        }
    }

    private String validarTransferencia(TransferenciaDTO transferenciaDTO) {
        validarDadosUsuarioTransferencia(transferenciaDTO);
        try {
            return mockAutorizadorFeignClient.autorizadorTrasferencia().getMessage();
        } catch (FeignException e) {
            throw new AutorizadorException(ConstantsUtil.ERRO_AUTORIZADOR);
        }
    }


    private TransferenciaDTO salvarTransferencia(TransferenciaDTO transferenciaDTO) {
        Transferencia transferencia = transferenciaMapper.toEntity(transferenciaDTO);
        transferencia.setData(LocalDateTime.now());
        return transferenciaMapper.toDto(transferenciaRepository.save(transferencia));
    }
}
