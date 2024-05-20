package com.example.softwareContabilidade.controller;

import com.example.softwareContabilidade.model.Compra;
import com.example.softwareContabilidade.model.Produto;
import com.example.softwareContabilidade.model.IcmsReceber;
import com.example.softwareContabilidade.repository.CompraRepository;
import com.example.softwareContabilidade.repository.IcmsReceberRepository;
import com.example.softwareContabilidade.repository.ProdutoRepository;
//import com.example.softwareContabilidade.repository.icmsReceberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private IcmsReceberRepository icmsReceberRepository;

    @GetMapping("/add")
    public String showForm(Model model) {
        model.addAttribute("compra", new Compra());
        List<Produto> produtos = produtoRepository.findAll();
        model.addAttribute("produtos", produtos);
        return "add-compra";
    }

    @PostMapping("/add")
    public String processCompra(Compra compra) {

        BigDecimal valorTotal = compra.getValorFinal();

        BigDecimal icms = valorTotal.multiply(BigDecimal.valueOf(0.18));

        compraRepository.save(compra);

        IcmsReceber icmsReceber = new IcmsReceber();
        icmsReceber.setValor(icms);
        icmsReceber.setCompra(compra);
        icmsReceberRepository.save(icmsReceber);


        return "redirect:/compras/add";// Redireciona para a lista de compras ou página de confirmação
    }

    @GetMapping("/listar")
    public String listarCompras(Model model) {
        List<Compra> compras = compraRepository.findAll();
        model.addAttribute("compras", compras);
        return "listar-compras";
    }
}
