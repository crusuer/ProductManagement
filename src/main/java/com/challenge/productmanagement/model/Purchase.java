package com.challenge.productmanagement.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String buyerEmail;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Calendar purchaseTime;
}

