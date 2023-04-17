package com.springboot.magicBooks.service;

import com.springboot.magicBooks.database.BookDatabase;
import com.springboot.magicBooks.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookDatabase bookDatabase;

    public List<Book> getAllBooks() {
        return this.bookDatabase.findAll();
    }
    public void insertOrUpdateBook(Book book){
        this.bookDatabase.save(book);
    }
    public void deleteBook(int id) {
        this.bookDatabase.deleteById(id);
    }

    public Book getBookById(int bookId) {
        Optional<Book> option = this.bookDatabase.findById(bookId);
        if (option.isPresent()) {
            return option.get();
        }

        return null;
    }
    public List<Book> searchByIdOrAuthor(String searchData) {
        return this.bookDatabase.findByIdOrAuthor(Integer.valueOf(searchData), searchData);
    }
    public List<Book> searchByAuthor(String author) {
        return this.bookDatabase.findByAuthor(author);
    }

    public List<Book> booksForBookIds(List<Integer> bookids) {
        return this.bookDatabase.findAllById(bookids);
    }

    public List<Book> searchBasedOnFilter(String searchData, String selectValue) {
        List<Book> books = new ArrayList<>();
        System.out.println("Inside search method");
        switch (selectValue) {
            case "PUBLICATION":
                books.addAll(this.bookDatabase.findAllByPublication(searchData));
                break;
            case "AUTHOR":
                books.addAll(this.bookDatabase.findAllByAuthor(searchData));
                break;
            case "PRICE":
                books.addAll(this.bookDatabase.findAllByPrice(searchData));
                break;
            case "TITLE":
                books.addAll(this.bookDatabase.findAllByTitle(searchData));
                break;
            case "ID":
                Optional<Book> opt = this.bookDatabase.findById(Integer.valueOf(searchData));
                if (opt.isPresent()) {
                    books.add(opt.get());
                }

                break;

        }
        return books;
    }
}
