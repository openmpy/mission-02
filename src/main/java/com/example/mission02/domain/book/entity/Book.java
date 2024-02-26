package com.example.mission02.domain.book.entity;

import com.example.mission02.global.entity.Timestamped;
import jakarta.persistence.*;

@Entity
public class Book extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String publisher;

    private boolean isLoaned;
}
