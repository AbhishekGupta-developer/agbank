package com.myorganisation.agbank.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bank")
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountno", nullable = false, unique = true)
    private String accountno;

    @Column(nullable = false)
    private Double balance;
}
