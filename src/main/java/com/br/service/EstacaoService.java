package com.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.exception.BusinessException;
import com.br.exception.ResourceNotFoundException;
import com.br.model.Estacao;
import com.br.repository.EstacaoRepository;

@Service
public class EstacaoService {

    @Autowired
    private EstacaoRepository estacaoRepository;

    public List<Estacao> listarTodos() {
        return estacaoRepository.findAll(Sort.by("id"));
    }

    public Estacao buscarPorId(Long id) {
        return estacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estação não encontrada com id: " + id));
    }

    public Estacao criar(Estacao estacao) {
        validarCodigoDuplicado(estacao.getCodigoestacao(), null);
        return estacaoRepository.save(estacao);
    }

    public Estacao atualizar(Long id, Estacao detalhes) {
        Estacao estacao = buscarPorId(id);
        validarCodigoDuplicado(detalhes.getCodigoestacao(), id);

        estacao.setNome(detalhes.getNome());
        estacao.setCodigoestacao(detalhes.getCodigoestacao());
        estacao.setEndereco(detalhes.getEndereco());
        estacao.setCapacidadeMediaUsuarios(detalhes.getCapacidadeMediaUsuarios());
        estacao.setAtiva(detalhes.getAtiva());

        return estacaoRepository.save(estacao);
    }

    public void excluir(Long id) {
        Estacao estacao = buscarPorId(id);
        estacaoRepository.delete(estacao);
    }

    private void validarCodigoDuplicado(Integer codigo, Long idAtual) {
        if (codigo == null) {
            return;
        }

        boolean duplicado = idAtual == null
                ? estacaoRepository.existsByCodigoestacao(codigo)
                : estacaoRepository.existsByCodigoestacaoAndIdNot(codigo, idAtual);

        if (duplicado) {
            throw new BusinessException("Já existe uma estação com o código " + codigo + ".");
        }
    }
}
