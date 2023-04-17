package com.springboot.magicBooks.controllers;

import com.springboot.magicBooks.entity.Book;
import com.springboot.magicBooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/book/{id}")
    public String openBookInsertionPage(@PathVariable("id") int id, Map<String, Book> map) {

        Book book = this.bookService.getBookById(id);
        if(book!=null)
        {
            map.put("book", book);
        }
        return "book";
    }

    @GetMapping("/book")
    public String openBookPage() {
        return "book";
    }

    @PostMapping("/book")
    public String insertBook(Book book) {
        this.bookService.insertOrUpdateBook(book);
        return "redirect:/admin";
    }

    @GetMapping("/delete")
    public String deleteBookById(@RequestParam("bookid")int bookid) {
        this.bookService.deleteBook(bookid);
        return "redirect:/admin";
    }

    @GetMapping("/book/search")
    public String searchByIdOrAuthor(@RequestParam String searchData) {
        return "redirect:/admin/search/"+searchData;
    }

    @GetMapping("/book/dashboard/search")
    public String searchBasedOnDifferentFilters(@RequestParam("searchData") String searchData, @RequestParam("selectValue")String selectValue) {
        return "redirect:/dashboard/search?"+"searchData="+searchData+"&"+"selectValue="+selectValue;
    }
}
