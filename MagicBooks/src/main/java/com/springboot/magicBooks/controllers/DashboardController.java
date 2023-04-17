package com.springboot.magicBooks.controllers;

import com.springboot.magicBooks.entity.Book;
import com.springboot.magicBooks.entity.LikedBooks;
import com.springboot.magicBooks.entity.ReadLaterBooks;
import com.springboot.magicBooks.service.BookService;
import com.springboot.magicBooks.service.LikedService;
import com.springboot.magicBooks.service.ReadLaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {
    @Autowired
    private BookService bookService;
    @Autowired
    private LikedService likedService;
    @Autowired
    private ReadLaterService readLaterService;

    @GetMapping("/dashboard")
    public String dashboard(Map<String, List<Book>> map) {
        map.put("books", this.bookService.getAllBooks());
        return "dashboard";
    }
    @GetMapping("/dashboard/search")
    public String searchBasedOnFilter(@RequestParam("searchData")String searchData, @RequestParam("selectValue")String selectValue,Map<String, List<Book>> map) {
        List<Book> books  = this.bookService.searchBasedOnFilter(searchData,selectValue);
        map.put("books", books);
        return "dashboard";
    }
    @GetMapping("/likedbook/delete")
    public String deleteFromLikedBook(@RequestParam("bookid") Integer bookid, HttpSession session) {
        likedService.deleteLikedBooks(session.getAttribute("email").toString(), bookid);
        return "redirect:/likedbook/page";

    }
    @GetMapping("/readlater/delete")
    public String deleteFromReadLater(@RequestParam("bookid") Integer bookid, HttpSession session) {

        readLaterService.deleteReadLaterBooks(session.getAttribute("email").toString(), bookid);
        return "redirect:/readlater/page";

    }

    @GetMapping("/likedbook/page")
    public String openLikedBooks(Map<String, List<Book>> map, HttpSession session) {
        if (session != null) {
            if (session.getAttribute("email") != null) {
                List<Integer> listOfBookId = this.likedService
                        .fetchAllBooksListForUser(session.getAttribute("email").toString());
                map.put("books", bookService.booksForBookIds(listOfBookId));
            }
        }
        return "liked";

    }

    @GetMapping("/readlater/page")
    public String openReadLater(Map<String, List<Book>> map, HttpSession session) {
        if (session != null) {
            if (session.getAttribute("email") != null) {
                List<Integer> listOfBookId = this.readLaterService
                        .fetchAllBooksListForUser(session.getAttribute("email").toString());

                map.put("books", bookService.booksForBookIds(listOfBookId));
            }
        }
        return "readLater";
    }

    @GetMapping("/readlater")
    public String insertInReadLater(@RequestParam("id") Integer id, HttpSession session) {

        ReadLaterBooks readLater = new ReadLaterBooks();
        readLater.setBookid(id);
        readLater.setUseremail(session.getAttribute("email").toString());
        readLaterService.insert(readLater);
        return "redirect:/dashboard";
    }

    @GetMapping("/likedbook")
    public String insertIntoLikedBook(@RequestParam("id")Integer id, HttpSession session) {
        LikedBooks books = new LikedBooks();
        books.setBookid(id);
        books.setUseremail(session.getAttribute("email").toString());
        likedService.insert(books);
        return "redirect:/dashboard";

    }
}
