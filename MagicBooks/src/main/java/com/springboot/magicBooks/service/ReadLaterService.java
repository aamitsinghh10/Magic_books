package com.springboot.magicBooks.service;

import com.springboot.magicBooks.database.ReadLaterDatabase;
import com.springboot.magicBooks.entity.ReadLaterBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReadLaterService {
    @Autowired
    private ReadLaterDatabase readLaterDatabase;

    public void insert(ReadLaterBooks readLater) {
        if(readLaterDatabase.findByUseremailAndBookid(readLater.getUseremail(),readLater.getBookid())==null) {
            this.readLaterDatabase.save(readLater);
        }
    }

    public List<Integer> fetchAllBooksListForUser(String email) {
        List<ReadLaterBooks> books = this.readLaterDatabase.findAllByUseremail(email);
        List<Integer> bookIds = new ArrayList<>();
        for (ReadLaterBooks book : books) {
            bookIds.add(book.getBookid());
        }
        return bookIds;
    }

    public void deleteReadLaterBooks(String email, int bookid) {
        ReadLaterBooks book = this.readLaterDatabase.findByUseremailAndBookid(email,bookid);
        this.readLaterDatabase.delete(book);
    }
}
