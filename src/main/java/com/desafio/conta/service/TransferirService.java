package com.desafio.conta.service;

import com.desafio.conta.error.AutorizadorException;
import com.desafio.conta.error.RegraNegocioException;
import com.desafio.conta.service.dto.DadosTransferenciaDTO;
import com.desafio.conta.service.dto.MessagemDTO;
import com.desafio.conta.service.dto.TransferenciaDTO;
import com.desafio.conta.service.enumeration.AutorizadorEnum;
import com.desafio.conta.service.feign.MockAutorizadorFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TransferirService {

    private final UsuarioService usuarioService;

    private final ContaService contaService;

    private final MockAutorizadorFeignClient mockAutorizadorFeignClient;

    @Transactional(rollbackFor = ExecutionException.class, timeout = 5)
    public MessagemDTO transferir(TransferenciaDTO transferenciaDTO) {
        DadosTransferenciaDTO dados = usuarioService.obterDadosTransferencia(transferenciaDTO);
        if (validarTransferencia(dados, transferenciaDTO).equals(AutorizadorEnum.AUTORIZADO.getDescricao())) {
            contaService.obterAtualizarValoresConta(transferenciaDTO.getIdUsuarioDestinatario(), dados.getIdUsuario(), transferenciaDTO.getValor());
            return concluirTransferencia();
        }
        throw new AutorizadorException("Transferencia não autorizada");

    }

    private void validarDadosUsuarioTransferencia(DadosTransferenciaDTO dados, TransferenciaDTO transferenciaDTO) {
        if (dados == null) {
            throw new RegraNegocioException("Usuario Remetente não encontrado");
        }
        if (transferenciaDTO.getValor() > dados.getValor()) {
            throw new RegraNegocioException("Usuário não possui valor para transferência");
        }
        usuarioService.obterPorId(transferenciaDTO.getIdUsuarioDestinatario());

    }

    private String validarTransferencia(DadosTransferenciaDTO dados, TransferenciaDTO transferenciaDTO) {
        validarDadosUsuarioTransferencia(dados, transferenciaDTO);

        try {
            return mockAutorizadorFeignClient.autorizadorTrasferencia().getMessage();
        } catch (FeignException e) {
            throw new AutorizadorException("not.found");
        }
    }


    private MessagemDTO concluirTransferencia() {
        try {
            MessagemDTO mensagem = mockAutorizadorFeignClient.verificarConclusaoTransferencia();
            if (mensagem.getMessage().equals(AutorizadorEnum.ENVIADO.getDescricao())) {
                return mensagem;
            }
            throw new AutorizadorException("Transferencia não autorizada");
        } catch (FeignException e) {
            throw new AutorizadorException("not.found");
        }
    }
}
