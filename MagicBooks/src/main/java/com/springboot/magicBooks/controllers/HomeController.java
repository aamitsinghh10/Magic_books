package com.springboot.magicBooks.controllers;

import com.springboot.magicBooks.entity.Book;
import com.springboot.magicBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private BookService bookService;

    @GetMapping("/index")
    public String getHome(Map<String, List<Book>> map) {

        map.put("books", this.bookService.getAllBooks());
        return "index";
    }
}
