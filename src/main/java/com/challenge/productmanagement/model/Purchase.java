package com.challenge.productmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String buyerEmail;
    @ManyToMany
    @JoinColumn(name = "product_id")
    private List<Product> products;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Calendar purchaseTime;
    @Column
    private BigDecimal totalValue;
}

