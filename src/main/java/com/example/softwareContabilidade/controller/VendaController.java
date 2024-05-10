package com.example.softwareContabilidade.controller;

import ch.qos.logback.core.CoreConstants;
import com.example.softwareContabilidade.model.Cliente;
import com.example.softwareContabilidade.model.Produto;
import com.example.softwareContabilidade.model.Venda;
import com.example.softwareContabilidade.model.VendaProduto;
import com.example.softwareContabilidade.repository.ClienteRepository;
import com.example.softwareContabilidade.repository.ProdutoRepository;
import com.example.softwareContabilidade.repository.VendaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

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

        BigDecimal valorTotal = venda.getValorTotal();
        BigDecimal custoTotal = calcularCustoTotal(venda);
        BigDecimal lucroBruto = valorTotal.subtract(custoTotal);


        venda.setCustoTotal(custoTotal);
        venda.setLucroBruto(lucroBruto);

        vendaRepository.save(venda);
        return "redirect:/vendas/add";
    }

    public BigDecimal calcularCustoTotal(Venda venda) {
        BigDecimal custoTotal = BigDecimal.ZERO;

        // calcular o custo total dos produtos vendidos
        for (VendaProduto vendaProduto : venda.getVendaProdutos()) {
            BigDecimal precoCompra = vendaProduto.getProduto().getPrecoCompra();
            int quantidade = vendaProduto.getQuantidade();
            BigDecimal custoProduto = precoCompra.multiply(BigDecimal.valueOf(quantidade));
            custoTotal = custoTotal.add(custoProduto);
        }

        return custoTotal;
    }
}



