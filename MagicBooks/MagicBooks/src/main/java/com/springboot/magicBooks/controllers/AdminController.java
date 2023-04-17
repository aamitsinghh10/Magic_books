package com.springboot.magicBooks.controllers;

import com.springboot.magicBooks.entity.Book;
import com.springboot.magicBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private BookService bookService;
    @GetMapping("/admin")
    public String getAdminPage(Map<String, List<Book>> map) {
        map.put("books", this.bookService.getAllBooks());
        return "admin";
    }
    @GetMapping("/admin/search/{searchString}")
    public String listSearchedBooksOnAdmin(@PathVariable String searchString, Map<String, List<Book>> map) {
        List<Book> books = new ArrayList<>();
        if (searchString != null && !searchString.isEmpty()) {
            char firstChar = searchString.charAt(0);
            if (Character.isLetter(firstChar)) {
                books = bookService.searchByAuthor(searchString.toLowerCase());
            } else if (Character.isDigit(firstChar)) {
                Book singlebook = bookService.getBookById(Integer.valueOf(searchString));
                if (singlebook != null) {
                    books.add(singlebook);
                }
            }
        }
        map.put("books", books);
        return "admin";
    }

}
