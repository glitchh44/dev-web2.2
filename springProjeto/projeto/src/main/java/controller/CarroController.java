// src/main/java/com/example/projeto/controller/CarroController.java
package com.example.projeto.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projeto.service.CarroService;
import com.example.projeto.model.Carro;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public List<Carro> listarCarros() {
        return carroService.listarCarros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarCarro(@PathVariable Long id) {
        return carroService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carro criarCarro(@RequestBody Carro carro) {
        return carroService.salvarCarro(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id,
                                                @RequestBody Carro dados) {
        return carroService.buscarPorId(id)
            .map(existing -> {
                existing.setModelo(dados.getModelo());
                existing.setMarca(dados.getMarca());
                existing.setAno(dados.getAno());
                existing.setValor(dados.getValor());
                Carro atualizado = carroService.salvarCarro(existing);
                return ResponseEntity.ok(atualizado);
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Long id) {
        carroService.deletarCarro(id);
        return ResponseEntity.noContent().build();
    }
}
