package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.Estacao;

@Repository
public interface EstacaoRepository extends JpaRepository<Estacao, Long> {
    boolean existsByCodigoestacao(Integer codigoestacao);
    boolean existsByCodigoestacaoAndIdNot(Integer codigoestacao, Long id);
}
