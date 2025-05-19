// src/main/java/com/example/projeto/controller/ProdutoWebController.java
package com.example.projeto.controller;

import com.example.projeto.model.Produto;
import com.example.projeto.service.ProdutoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoWebController {

    private final ProdutoService produtoService;

    public ProdutoWebController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // Redireciona para listagem
    @GetMapping
    public String index() {
        return "redirect:/produtos/listar";
    }

    // Página de formulário para cadastro
    @GetMapping("/cadastrar")
    public String exibirFormCadastro(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/form";
    }

    @PostMapping("/cadastrar")
    public String cadastrarProduto(
            @Valid @ModelAttribute("produto") Produto produto,
            BindingResult result,
            RedirectAttributes ra) {

        if (result.hasErrors()) {
            return "produtos/form";
        }
        produtoService.salvarProduto(produto);
        ra.addFlashAttribute("success", "Produto cadastrado com sucesso!");
        return "redirect:/produtos/listar";
    }

    // Página de listagem
    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        model.addAttribute("lista", produtoService.listarProdutos());
        return "produtos/lista";
    }

    // Detalhes e exclusão
    @GetMapping("/{id}")
    public String detalhesProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoService.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        model.addAttribute("produto", produto);
        return "produtos/detalhe";
    }

    @PostMapping("/{id}/excluir")
    public String excluirProduto(@PathVariable Long id, RedirectAttributes ra) {
        produtoService.deletarProduto(id);
        ra.addFlashAttribute("success", "Produto excluído com sucesso!");
        return "redirect:/produtos/listar";
    }
}
