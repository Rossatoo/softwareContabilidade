package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.model.Fornecedor;
import com.example.softwareContabilidade.repository.FornecedorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorController(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("fornecedor", new Fornecedor());
        return "add-fornecedor";
    }

    @PostMapping("/add")
    public String addFornecedor(Fornecedor fornecedor) {
        fornecedorRepository.save(fornecedor);
        return "redirect:/fornecedores/add";
    }

    @GetMapping("/listar")
    public String listarFornecedores(Model model) {
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        model.addAttribute("fornecedores", fornecedores);
        return "listar-fornecedores";
    }
}
