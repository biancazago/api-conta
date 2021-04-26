package com.desafio.conta.repository;

import com.desafio.conta.domain.Usuario;
import com.desafio.conta.service.dto.DadosTransferenciaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT true FROM Usuario u WHERE u.cpfCnpj = :cpfCnpj OR u.email = :email")
    Boolean validacaoUsuario(@Param("cpfCnpj") String cpfCnpj, @Param("email") String email);

    @Query("SELECT new com.desafio.conta.service.dto.DadosTransferenciaDTO(u.id, c.saldo, (SELECT ud.id FROM Usuario ud WHERE ud.id = :idUsuarioDestinatario)) FROM Usuario u " +
            " JOIN Conta c ON c.usuario.id = u.id " +
            " WHERE u.id = :idUsuarioRemetente AND u.tipoUsuario = 'COMUM' ")
    DadosTransferenciaDTO obterDadosTransferencia(@Param("idUsuarioRemetente") Long idUsuario, @Param("idUsuarioDestinatario") Long idUsuarioDestinatario);

}
