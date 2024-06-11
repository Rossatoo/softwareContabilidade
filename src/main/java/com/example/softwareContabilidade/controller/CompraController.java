package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.model.Compra;
import com.example.softwareContabilidade.model.Empresa;
import com.example.softwareContabilidade.model.Patrimonio;
import com.example.softwareContabilidade.repository.CompraRepository;
import com.example.softwareContabilidade.repository.EmpresaRepository;
import com.example.softwareContabilidade.repository.PatrimonioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/compras")
public class CompraController {

    private final CompraRepository compraRepository;
    private final EmpresaRepository empresaRepository;
    private final PatrimonioRepository patrimonioRepository;

    public CompraController(CompraRepository compraRepository, EmpresaRepository empresaRepository, PatrimonioRepository patrimonioRepository) {
        this.compraRepository = compraRepository;
        this.empresaRepository = empresaRepository;
        this.patrimonioRepository = patrimonioRepository;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("compra", new Compra());
        // Adicione os outros atributos necessários ao modelo
        return "add-compra";
    }

    @PostMapping("/add")
    public String addCompra(Compra compra) {
        Empresa empresa = empresaRepository.findById(1L).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        BigDecimal valorCompra = compra.getValorFinal();

        if (empresa.getCaixa().compareTo(valorCompra) >= 0) {
            empresa.setCaixa(empresa.getCaixa().subtract(valorCompra));
            empresaRepository.save(empresa);

            Patrimonio patrimonio = new Patrimonio();
            patrimonio.setNome(compra.getProduto().getNome());
            patrimonio.setValorCompra(valorCompra);
            patrimonio.setFornecedor(compra.getProduto().getFornecedor());
            //patrimonio.setDataCompra(compra.getDataCompra());
            patrimonioRepository.save(patrimonio);

            compraRepository.save(compra);
        } else {
            // Lógica para lidar com falta de fundos
        }

        return "redirect:/compras/add";
    }

    @GetMapping("/listar")
    public String listarCompras(Model model) {
        model.addAttribute("compras", compraRepository.findAll());
        return "listar-compras";
    }
}
