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
    @Query(value = "UPDATE Conta c SET valor = valor + :valor WHERE c.id = :id")
    void adicionarDinheiroConta(@Param("id") Long id, @Param("valor") Double valor);

}
