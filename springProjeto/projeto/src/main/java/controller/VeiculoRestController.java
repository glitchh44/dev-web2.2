package com.example.projeto.controller;

import com.example.projeto.model.Carro;
import com.example.projeto.service.CarroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoRestController {

    private final CarroService carroService;

    public VeiculoRestController(CarroService carroService) {
        this.carroService = carroService;
    }

    @GetMapping
    public List<Carro> listarVeiculos() {
        return carroService.listarCarros();
    }
}
