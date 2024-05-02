package com.example.softwareContabilidade.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "icms_a_receber")
public class icmsReceber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    private Compra compra;
}
