package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.model.Fornecedor;
import com.example.softwareContabilidade.model.Patrimonio;
import com.example.softwareContabilidade.repository.FornecedorRepository;
import com.example.softwareContabilidade.repository.PatrimonioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/patrimonios")
public class PatrimonioController {

    private final PatrimonioRepository patrimonioRepository;
    private final FornecedorRepository fornecedorRepository;

    public PatrimonioController(PatrimonioRepository patrimonioRepository, FornecedorRepository fornecedorRepository) {
        this.patrimonioRepository = patrimonioRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patrimonio", new Patrimonio());
        List<Fornecedor> fornecedores = fornecedorRepository.findAll();
        model.addAttribute("fornecedores", fornecedores);
        return "add-patrimonio";
    }

    @PostMapping("/add")
    public String addPatrimonio(Patrimonio patrimonio) {
        patrimonioRepository.save(patrimonio);
        return "redirect:/patrimonios/add";
    }

    @GetMapping("/listar")
    public String listarPatrimonios(Model model){
        List<Patrimonio> patrimonios = patrimonioRepository.findAll();
        model.addAttribute("patrimonios", patrimonios);
        return "listar-patrimonios";
    }
}
