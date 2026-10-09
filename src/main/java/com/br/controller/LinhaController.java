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

import com.br.model.Linha;
import com.br.service.LinhaService;

@RestController
@RequestMapping("/api/linhas")
@CrossOrigin(origins = "http://localhost:5173")
public class LinhaController {

    @Autowired
    private LinhaService linhaService;

    @GetMapping
    public List<Linha> listarTodos() {
        return linhaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Linha buscarPorId(@PathVariable Long id) {
        return linhaService.buscarPorId(id);
    }

    @PostMapping
    public Linha criar(@RequestBody Linha linha) {
        return linhaService.criar(linha);
    }

    @PutMapping("/{id}")
    public Linha atualizar(@PathVariable Long id, @RequestBody Linha linha) {
        return linhaService.atualizar(id, linha);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        linhaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
