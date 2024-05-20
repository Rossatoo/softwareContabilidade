package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.model.Cliente;
import com.example.softwareContabilidade.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }


    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("cliente", new Cliente());
        return "add-cliente";
    }

    @PostMapping("/add")
    public String addCliente(Cliente cliente){
        clienteRepository.save(cliente);
        return "redirect:/clientes/add";
    }

    @GetMapping("/listar")
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "listar-clientes";
    }
}
