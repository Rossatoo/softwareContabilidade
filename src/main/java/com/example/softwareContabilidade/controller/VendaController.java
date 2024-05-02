package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.model.Cliente;
import com.example.softwareContabilidade.model.Venda;
import com.example.softwareContabilidade.repository.ClienteRepository;
import com.example.softwareContabilidade.repository.ProdutoRepository;
import com.example.softwareContabilidade.repository.VendaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    private final VendaRepository vendaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public VendaController(VendaRepository vendaRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("venda", new Venda());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("produtos", produtoRepository.findAll());
        return "add-venda";
    }

    @PostMapping("/add")
    public String addVenda(Venda venda) {
        vendaRepository.save(venda);
        return "redirect:/vendas/add";
    }
}



