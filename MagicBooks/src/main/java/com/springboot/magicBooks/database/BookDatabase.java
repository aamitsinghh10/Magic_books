package com.springboot.magicBooks.database;

import com.springboot.magicBooks.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDatabase extends JpaRepository<Book,Integer> {
    List<Book> findByIdOrAuthor(int id , String author);
    List<Book> findByAuthor(String author);
    List<Book> findAllByPublication(String searchData);
    List<Book> findAllByAuthor(String searchData);
    List<Book> findAllByPrice(String searchData);
    List<Book> findAllByTitle(String searchData);
}

