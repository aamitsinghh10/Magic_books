package com.springboot.magicBooks.database;

import com.springboot.magicBooks.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminDatabase extends JpaRepository<Admin, String> {
    List<Admin> findAllByApproved(boolean flag);
}
