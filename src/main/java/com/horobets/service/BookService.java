package com.horobets.service;

import com.horobets.entity.Book;
import com.horobets.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository BookRepository;

    public List<Book> getAllBook() {
        return BookRepository.findAll();
    }

    public List<Book> getAllBookByUserId(Long userId) {
        return BookRepository.findAllByUserId(userId);
    }

    public Book saveBook(Book book) {
        return BookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        BookRepository.deleteById(id);
    }
    public Book getBookById(Long id) {
        return BookRepository.getBookById(id);
    }
}