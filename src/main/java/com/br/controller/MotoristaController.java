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

import com.br.model.Motorista;
import com.br.service.MotoristaService;

@RestController
@RequestMapping("/api/motoristas")
@CrossOrigin(origins = "http://localhost:5173")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @GetMapping
    public List<Motorista> listarTodos() {
        return motoristaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Motorista buscarPorId(@PathVariable Long id) {
        return motoristaService.buscarPorId(id);
    }

    @PostMapping
    public Motorista criar(@RequestBody Motorista motorista) {
        return motoristaService.criar(motorista);
    }

    @PutMapping("/{id}")
    public Motorista atualizar(@PathVariable Long id, @RequestBody Motorista motorista) {
        return motoristaService.atualizar(id, motorista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        motoristaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
