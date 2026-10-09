package com.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.exception.BusinessException;
import com.br.exception.ResourceNotFoundException;
import com.br.model.Veiculo;
import com.br.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll(Sort.by("id"));
    }

    public Veiculo buscarPorId(Long id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veículo não encontrado com id: " + id));
    }

    public Veiculo criar(Veiculo veiculo) {
        validarDuplicidade(veiculo, null);
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo detalhes) {
        Veiculo veiculo = buscarPorId(id);
        validarDuplicidade(detalhes, id);

        veiculo.setNumeroFrota(detalhes.getNumeroFrota());
        veiculo.setPlaca(detalhes.getPlaca());
        veiculo.setModelo(detalhes.getModelo());
        veiculo.setFabricante(detalhes.getFabricante());
        veiculo.setDataDeFabricacao(detalhes.getDataDeFabricacao());
        veiculo.setCapacidadePassageiros(detalhes.getCapacidadePassageiros());
        veiculo.setConsumoPorKM(detalhes.getConsumoPorKM());
        veiculo.setAtivo(detalhes.getAtivo());

        return veiculoRepository.save(veiculo);
    }

    public void excluir(Long id) {
        Veiculo veiculo = buscarPorId(id);
        veiculoRepository.delete(veiculo);
    }

    private void validarDuplicidade(Veiculo veiculo, Long idAtual) {
        if (veiculo.getPlaca() != null && !veiculo.getPlaca().isBlank()) {
            boolean placaDuplicada = idAtual == null
                    ? veiculoRepository.existsByPlaca(veiculo.getPlaca())
                    : veiculoRepository.existsByPlacaAndIdNot(veiculo.getPlaca(), idAtual);
            if (placaDuplicada) {
                throw new BusinessException("Já existe um veículo com a placa informada.");
            }
        }

        if (veiculo.getNumeroFrota() != null) {
            boolean numeroDuplicado = idAtual == null
                    ? veiculoRepository.existsByNumeroFrota(veiculo.getNumeroFrota())
                    : veiculoRepository.existsByNumeroFrotaAndIdNot(veiculo.getNumeroFrota(), idAtual);
            if (numeroDuplicado) {
                throw new BusinessException("Já existe um veículo com esse número de frota.");
            }
        }
    }
}
