package com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.model.Estacao;
import com.br.service.EstacaoService;

@RestController
@RequestMapping("/api/estacoes")
@CrossOrigin(origins = "http://localhost:5173")
public class EstacaoController {

    @Autowired
    private EstacaoService estacaoService;

    @GetMapping
    public List<Estacao> listarTodos() {
        return estacaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Estacao buscarPorId(@PathVariable Long id) {
        return estacaoService.buscarPorId(id);
    }

    @PostMapping
    public Estacao criar(@RequestBody Estacao estacao) {
        return estacaoService.criar(estacao);
    }

    @PutMapping("/{id}")
    public Estacao atualizar(@PathVariable Long id, @RequestBody Estacao estacao) {
        return estacaoService.atualizar(id, estacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        estacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
