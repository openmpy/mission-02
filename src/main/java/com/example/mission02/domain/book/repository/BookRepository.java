package com.example.mission02.domain.book.repository;

import com.example.mission02.domain.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByCreatedAtAsc();
}
