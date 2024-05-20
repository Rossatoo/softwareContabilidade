package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.model.Produto;
import com.example.softwareContabilidade.repository.FornecedorRepository;
import com.example.softwareContabilidade.repository.ProdutoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;

    public ProdutoController(ProdutoRepository produtoRepository, FornecedorRepository fornecedorRepository) {
        this.produtoRepository = produtoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("fornecedores", fornecedorRepository.findAll());
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduto(Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/produtos/add";
    }

    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "listar-produtos";
    }
}
