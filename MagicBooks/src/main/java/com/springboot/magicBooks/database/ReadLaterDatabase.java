package com.springboot.magicBooks.database;

import com.springboot.magicBooks.entity.ReadLaterBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadLaterDatabase extends JpaRepository<ReadLaterBooks, Integer> {
    List<ReadLaterBooks> findAllByUseremail(String useremail);
    ReadLaterBooks findByUseremailAndBookid(String useremail, int bookid);
}
