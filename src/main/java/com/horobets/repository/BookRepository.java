package com.horobets.repository;

import com.horobets.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();
    List<Book> findAllByUserId(Long userId);
    void deleteById(Long id);
    Book getBookById(Long id);
}