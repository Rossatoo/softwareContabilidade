package com.example.softwareContabilidade.Service;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BalancoPatrimonialDTO {
    private BigDecimal capitalSocial;
    private BigDecimal caixa;
    private BigDecimal totalPatrimonio;
    private BigDecimal totalAtivos;
}
