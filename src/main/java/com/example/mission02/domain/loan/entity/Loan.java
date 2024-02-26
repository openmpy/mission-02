package com.example.mission02.domain.loan.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long bookId;

    @Column(nullable = false)
    private Long userId;

    private boolean returned;

    @Column(nullable = false)
    private LocalDateTime loanedAt;

    private LocalDateTime returnedAt;
}
