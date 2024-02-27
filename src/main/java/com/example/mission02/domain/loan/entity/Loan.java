package com.example.mission02.domain.loan.entity;

import com.example.mission02.domain.book.entity.Book;
import com.example.mission02.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isReturned;

    @Column(nullable = false)
    private LocalDateTime loanedAt;

    private LocalDateTime returnedAt;

    public void returned(LocalDateTime returnedAt) {
        this.isReturned = true;
        this.returnedAt = returnedAt;
    }
}
