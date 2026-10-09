package com.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.br.exception.BusinessException;
import com.br.exception.ResourceNotFoundException;
import com.br.model.Motorista;
import com.br.repository.MotoristaRepository;

@Service
public class MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    public List<Motorista> listarTodos() {
        return motoristaRepository.findAll(Sort.by("id"));
    }

    public Motorista buscarPorId(Long id) {
        return motoristaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorista não encontrado com id: " + id));
    }

    public Motorista criar(Motorista motorista) {
        validarDuplicidade(motorista, null);
        return motoristaRepository.save(motorista);
    }

    public Motorista atualizar(Long id, Motorista detalhes) {
        Motorista motorista = buscarPorId(id);
        validarDuplicidade(detalhes, id);

        motorista.setNome(detalhes.getNome());
        motorista.setCpf(detalhes.getCpf());
        motorista.setMatricula(detalhes.getMatricula());
        motorista.setDataAdmissao(detalhes.getDataAdmissao());
        motorista.setCategoriaCnh(detalhes.getCategoriaCnh());
        motorista.setAtivo(detalhes.getAtivo());

        return motoristaRepository.save(motorista);
    }

    public void excluir(Long id) {
        Motorista motorista = buscarPorId(id);
        motoristaRepository.delete(motorista);
    }

    private void validarDuplicidade(Motorista motorista, Long idAtual) {
        if (motorista.getCpf() != null && !motorista.getCpf().isBlank()) {
            boolean cpfDuplicado = idAtual == null
                    ? motoristaRepository.existsByCpf(motorista.getCpf())
                    : motoristaRepository.existsByCpfAndIdNot(motorista.getCpf(), idAtual);
            if (cpfDuplicado) {
                throw new BusinessException("Já existe um motorista com o CPF informado.");
            }
        }

        if (motorista.getMatricula() != null && !motorista.getMatricula().isBlank()) {
            boolean matriculaDuplicada = idAtual == null
                    ? motoristaRepository.existsByMatricula(motorista.getMatricula())
                    : motoristaRepository.existsByMatriculaAndIdNot(motorista.getMatricula(), idAtual);
            if (matriculaDuplicada) {
                throw new BusinessException("Já existe um motorista com a matrícula informada.");
            }
        }
    }
}
