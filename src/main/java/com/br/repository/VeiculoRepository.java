package com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    boolean existsByPlaca(String placa);
    boolean existsByPlacaAndIdNot(String placa, Long id);
    boolean existsByNumeroFrota(Integer numeroFrota);
    boolean existsByNumeroFrotaAndIdNot(Integer numeroFrota, Long id);
}
