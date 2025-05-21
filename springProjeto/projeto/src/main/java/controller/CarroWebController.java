// src/main/java/com/example/projeto/controller/CarroWebController.java
package com.example.projeto.controller;

import com.example.projeto.model.Carro;
import com.example.projeto.service.CarroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/carros")
public class CarroWebController {

    private final CarroService carroService;

    public CarroWebController(CarroService carroService) {
        this.carroService = carroService;
    }

    // Redireciona /carros para /carros/listar
    @GetMapping
    public String redirecionarParaLista() {
        return "redirect:/carros/listar";
    }

    @GetMapping("/listar")
    public String listarCarros(Model model) {
        model.addAttribute("carros", carroService.listarCarros());
        return "carros/listar";
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        model.addAttribute("carro", new Carro());
        return "carros/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvarCarro(@ModelAttribute Carro carro) {
        carroService.salvarCarro(carro);
        return "redirect:/carros/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarCarro(@PathVariable Long id, Model model) {
        Optional<Carro> carroOptional = carroService.buscarPorId(id);
        if (carroOptional.isPresent()) {
            model.addAttribute("carro", carroOptional.get());
            return "carros/cadastrar";
        } else {
            return "redirect:/carros/listar";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deletarCarro(@PathVariable Long id) {
        carroService.deletarCarro(id);
        return "redirect:/carros/listar";
    }

    // ✅ Novo método para exibir os detalhes do carro
    @GetMapping("/{id}")
    public String detalhesCarro(@PathVariable Long id, Model model) {
        Optional<Carro> carroOptional = carroService.buscarPorId(id);
        if (carroOptional.isPresent()) {
            model.addAttribute("carro", carroOptional.get());
            return "carros/detalhes"; // Aponta para detalhes.html
        } else {
            return "redirect:/carros/listar";
        }
    }
}
