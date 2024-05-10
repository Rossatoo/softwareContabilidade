package com.example.softwareContabilidade.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<VendaProduto> vendaProdutos = new ArrayList<>();

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "custo_total")
    private BigDecimal custoTotal;

    @Column(name = "lucro_bruto")
    private BigDecimal lucroBruto;


    //method for add an order detail
    public void addOrderDetail(VendaProduto orderDetail){
        vendaProdutos.add(orderDetail);
        orderDetail.setVenda(this);
    }


}
