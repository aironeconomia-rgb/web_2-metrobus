package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.Linha;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, Long> {
    boolean existsByCodigolinha(Integer codigolinha);
    boolean existsByCodigolinhaAndIdNot(Integer codigolinha, Long id);
}
