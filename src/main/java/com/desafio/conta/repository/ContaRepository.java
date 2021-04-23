package com.desafio.conta.repository;

import com.desafio.conta.domain.Conta;
import com.desafio.conta.service.enumeration.TipoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Modifying
    @Query(value = "UPDATE Conta c SET valor = valor + CASE WHEN :tipo = :remetente " +
            " THEN - :valor ELSE :valor END " +
            " WHERE c.usuario.id = :idUsuario")
    void alterarValorConta(@Param("idUsuario") Long idUsuario, @Param("valor") Double valor, @Param("tipo") TipoEnum tipo, @Param("remetente") TipoEnum remetente);

    default void atualizarValorConta(@Param("idUsuario") Long idUsuario, @Param("valor") Double valor, @Param("tipo") TipoEnum tipo) {
        alterarValorConta(idUsuario, valor, tipo, TipoEnum.REMETENTE);
    }

    @Modifying
    @Query(value = "UPDATE Conta c SET valor = valor + :valor WHERE c.id = :id")
    void adicionarDinheiroConta(@Param("id") Long id, @Param("valor") Double valor);

}
