package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.Service.BalancoPatrimonialDTO;
import com.example.softwareContabilidade.Service.BalancoPatrimonialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/balanco")
public class BalancoPatrimonialController {

    private final BalancoPatrimonialService balancoPatrimonialService;

    public BalancoPatrimonialController(BalancoPatrimonialService balancoPatrimonialService) {
        this.balancoPatrimonialService = balancoPatrimonialService;
    }

    @GetMapping("/visualizar")
    public String visualizarBalanco(Model model) {
        BalancoPatrimonialDTO balanco = balancoPatrimonialService.gerarBalancoPatrimonial();
        model.addAttribute("balanco", balanco);
        return "visualizar-balanco";
    }
}
