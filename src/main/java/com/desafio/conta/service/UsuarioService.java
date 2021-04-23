package com.desafio.conta.service;

import com.desafio.conta.repository.UsuarioRepository;
import com.desafio.conta.service.dto.ContaDTO;
import com.desafio.conta.service.dto.DadosTransferenciaDTO;
import com.desafio.conta.service.dto.TransferenciaDTO;
import com.desafio.conta.service.dto.UsuarioDTO;
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
        if (Boolean.TRUE.equals(validarCadastroUsuario(usuarioDTO))) {
            criptografarSenha(usuarioDTO);
            usuarioDTO.setTipoUsuario(usuarioDTO.getCpfCnpj().length() == 14 ? TipoUsuarioEnum.LOJISTA : TipoUsuarioEnum.COMUM);
            Long id = usuarioRepository.saveAndFlush(usuarioMapper.toEntity(usuarioDTO)).getId();

            applicationEventPublisher.publishEvent(new ContaDTO(null, 0D, id));
        }
    }

    private Boolean validarCadastroUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.validacaoUsuario(usuarioDTO.getCpfCnpj(), usuarioDTO.getEmail()) != null) {
            throw new RegraNegocioException(ConstantsUtil.CPF_CPNJ_EMAIL_INVALIDO);
        }
        return true;
    }

    private void criptografarSenha(UsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
    }

    public UsuarioDTO obterPorId(Long id) {
        return usuarioMapper.toDto(usuarioRepository.findById(id).orElse(null));
    }

    public DadosTransferenciaDTO obterDadosTransferencia(TransferenciaDTO transferenciaDTO) {
        return usuarioRepository.obterDadosTransferencia(transferenciaDTO.getIdUsuarioRemetente());
    }


}
