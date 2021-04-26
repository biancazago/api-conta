package com.desafio.conta.service;

import com.desafio.conta.repository.UsuarioRepository;
import com.desafio.conta.service.dto.ContaDTO;
import com.desafio.conta.service.dto.DadosTransferenciaDTO;
import com.desafio.conta.service.dto.UsuarioDTO;
import com.desafio.conta.service.dto.UsuarioListDTO;
import com.desafio.conta.service.enumeration.TipoUsuarioEnum;
import com.desafio.conta.service.mapper.UsuarioMapper;
import com.desafio.conta.util.ConstantsUtil;
import com.desafio.conta.util.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationEventPublisher applicationEventPublisher;

    public void salvar(UsuarioDTO usuarioDTO) {
        validarCadastroUsuario(usuarioDTO);
        criptografarSenha(usuarioDTO);
        usuarioDTO.setTipoUsuario(usuarioDTO.getCpfCnpj().length() == 14 ? TipoUsuarioEnum.LOJISTA : TipoUsuarioEnum.COMUM);
        Long id = usuarioRepository.saveAndFlush(usuarioMapper.toEntity(usuarioDTO)).getId();

        applicationEventPublisher.publishEvent(new ContaDTO(null, 0D, id));
    }

    private void validarCadastroUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.validacaoUsuario(usuarioDTO.getCpfCnpj(), usuarioDTO.getEmail()) != null) {
            throw new RegraNegocioException(ConstantsUtil.CPF_CPNJ_EMAIL_INVALIDO);
        }
    }

    private void criptografarSenha(UsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
    }

    public UsuarioListDTO obterPorId(Long id) {
        return usuarioMapper.toDtoList(usuarioRepository.findById(id).orElseThrow(() -> new RegraNegocioException(ConstantsUtil.USUARIO_NAO_ENCONTRADO)));
    }

    public DadosTransferenciaDTO obterDadosTransferencia(DadosTransferenciaDTO transferenciaDTO) {
        DadosTransferenciaDTO dadosTransferenciaDTO = usuarioRepository.obterDadosTransferencia(transferenciaDTO.getIdUsuarioRemetente(), transferenciaDTO.getIdUsuarioDestinatario());
        if(dadosTransferenciaDTO == null) {
            throw new RegraNegocioException(ConstantsUtil.ERRO_DADOS_TRANSAFERENCIA);
        }
        return dadosTransferenciaDTO;
    }


}
