package com.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.exception.BusinessException;
import com.br.exception.ResourceNotFoundException;
import com.br.model.Linha;
import com.br.repository.LinhaRepository;

@Service
public class LinhaService {

    @Autowired
    private LinhaRepository linhaRepository;

    public List<Linha> listarTodos() {
        return linhaRepository.findAll(Sort.by("id"));
    }

    public Linha buscarPorId(Long id) {
        return linhaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Linha não encontrada com id: " + id));
    }

    public Linha criar(Linha linha) {
        validarCodigoDuplicado(linha.getCodigolinha(), null);
        return linhaRepository.save(linha);
    }

    public Linha atualizar(Long id, Linha detalhes) {
        Linha linha = buscarPorId(id);
        validarCodigoDuplicado(detalhes.getCodigolinha(), id);

        linha.setCodigolinha(detalhes.getCodigolinha());
        linha.setNome(detalhes.getNome());
        linha.setOrigem(detalhes.getOrigem());
        linha.setDestino(detalhes.getDestino());
        linha.setAtiva(detalhes.getAtiva());

        // Os relacionamentos serão tratados em uma etapa posterior,
        // como indicado na sequência das aulas (relacionamentos JPA/DTOs).
        return linhaRepository.save(linha);
    }

    public void excluir(Long id) {
        Linha linha = buscarPorId(id);
        linhaRepository.delete(linha);
    }

    private void validarCodigoDuplicado(Integer codigo, Long idAtual) {
        if (codigo == null) {
            return;
        }

        boolean duplicado = idAtual == null
                ? linhaRepository.existsByCodigolinha(codigo)
                : linhaRepository.existsByCodigolinhaAndIdNot(codigo, idAtual);

        if (duplicado) {
            throw new BusinessException("Já existe uma linha com o código " + codigo + ".");
        }
    }
}
