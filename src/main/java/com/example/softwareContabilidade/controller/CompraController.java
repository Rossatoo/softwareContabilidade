package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.model.Compra;
import com.example.softwareContabilidade.model.Produto;
import com.example.softwareContabilidade.model.icmsReceber;
import com.example.softwareContabilidade.repository.CompraRepository;
import com.example.softwareContabilidade.repository.ProdutoRepository;
//import com.example.softwareContabilidade.repository.icmsReceberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;


    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("compra", new Compra());
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "add-compra";
    }

    @PostMapping("/add")
    public String processCompra(Compra compra) {
        compraRepository.save(compra);
        return "redirect:/compras/add";// Redireciona para a lista de compras ou página de confirmação
    }
}
