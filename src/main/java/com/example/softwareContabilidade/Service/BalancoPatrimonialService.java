package com.example.softwareContabilidade.Service;

import com.example.softwareContabilidade.Service.BalancoPatrimonialDTO;
import com.example.softwareContabilidade.model.Empresa;
import com.example.softwareContabilidade.model.Patrimonio;
import com.example.softwareContabilidade.model.Compra;
import com.example.softwareContabilidade.model.Venda;
import com.example.softwareContabilidade.repository.EmpresaRepository;
import com.example.softwareContabilidade.repository.PatrimonioRepository;
import com.example.softwareContabilidade.repository.CompraRepository;
import com.example.softwareContabilidade.repository.VendaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BalancoPatrimonialService {

    private final EmpresaRepository empresaRepository;
    private final PatrimonioRepository patrimonioRepository;
    private final CompraRepository compraRepository;
    private final VendaRepository vendaRepository;

    public BalancoPatrimonialService(EmpresaRepository empresaRepository, PatrimonioRepository patrimonioRepository, CompraRepository compraRepository, VendaRepository vendaRepository) {
        this.empresaRepository = empresaRepository;
        this.patrimonioRepository = patrimonioRepository;
        this.compraRepository = compraRepository;
        this.vendaRepository = vendaRepository;
    }

    public BalancoPatrimonialDTO gerarBalancoPatrimonial() {
        Empresa empresa = empresaRepository.findById(1L).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        BigDecimal capitalSocial = empresa.getCapitalSocial();
        BigDecimal caixa = empresa.getCaixa();

        List<Patrimonio> patrimonios = patrimonioRepository.findAll();
        BigDecimal totalPatrimonio = patrimonios.stream()
                .map(Patrimonio::getValorCompra)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalAtivos = caixa.add(totalPatrimonio);

        List<Compra> compras = compraRepository.findAll();
        BigDecimal totalCompras = compras.stream()
                .map(Compra::getValorFinal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<Venda> vendas = vendaRepository.findAll();
        BigDecimal totalVendas = vendas.stream()
                .map(Venda::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal patrimonioLiquido = capitalSocial.add(totalVendas).subtract(totalCompras);

        return new BalancoPatrimonialDTO(capitalSocial, caixa, totalPatrimonio, totalAtivos, patrimonioLiquido);
    }
}
