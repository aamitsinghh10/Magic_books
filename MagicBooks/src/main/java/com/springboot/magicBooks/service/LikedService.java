package com.springboot.magicBooks.service;

import com.springboot.magicBooks.database.LikedDatabase;
import com.springboot.magicBooks.entity.LikedBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikedService {
    @Autowired
    private LikedDatabase likedDatabase;
    public void insert(LikedBooks books) {

        if (likedDatabase.findByUseremailAndBookid(books.getUseremail(),books.getBookid())==null) {
            this.likedDatabase.save(books);
        }
    }

    public List<Integer> fetchAllBooksListForUser(String email) {
        List<LikedBooks> books = this.likedDatabase.findAllByUseremail(email);
        List<Integer> bookIds = new ArrayList<>();
        for (LikedBooks book : books) {
            bookIds.add(book.getBookid());
        }
        return bookIds;
    }

    public void deleteLikedBooks(String email, int bookid) {
        LikedBooks book = this.likedDatabase.findByUseremailAndBookid(email,bookid);
        this.likedDatabase.delete(book);
    }
}
