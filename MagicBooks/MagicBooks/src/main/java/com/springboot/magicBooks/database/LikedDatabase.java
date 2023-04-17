package com.springboot.magicBooks.database;

import com.springboot.magicBooks.entity.LikedBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikedDatabase extends JpaRepository<LikedBooks, Integer> {

    List<LikedBooks> findAllByUseremail(String useremail);
    LikedBooks findByUseremailAndBookid(String useremail, int bookid);
}
