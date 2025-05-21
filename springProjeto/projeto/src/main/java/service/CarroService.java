// src/main/java/com/example/projeto/service/CarroService.java
package com.example.projeto.service;

import org.springframework.stereotype.Service;
import com.example.projeto.repository.CarroRepository;
import com.example.projeto.model.Carro;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public List<Carro> listarCarros() {
        return carroRepository.findAll();
    }

    public Optional<Carro> buscarPorId(Long id) {
        return carroRepository.findById(id);
    }

    public Carro salvarCarro(Carro carro) {
        return carroRepository.save(carro);
    }

    public void deletarCarro(Long id) {
        carroRepository.deleteById(id);
    }
}
