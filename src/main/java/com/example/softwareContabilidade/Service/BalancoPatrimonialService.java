package com.example.softwareContabilidade.Service;

import com.example.softwareContabilidade.model.Empresa;
import com.example.softwareContabilidade.model.Patrimonio;
import com.example.softwareContabilidade.repository.EmpresaRepository;
import com.example.softwareContabilidade.repository.PatrimonioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BalancoPatrimonialService {

    private final EmpresaRepository empresaRepository;
    private final PatrimonioRepository patrimonioRepository;

    public BalancoPatrimonialService(EmpresaRepository empresaRepository, PatrimonioRepository patrimonioRepository) {
        this.empresaRepository = empresaRepository;
        this.patrimonioRepository = patrimonioRepository;
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

        return new BalancoPatrimonialDTO(capitalSocial, caixa, totalPatrimonio, totalAtivos);
    }
}
