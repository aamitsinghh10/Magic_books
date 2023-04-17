package com.springboot.magicBooks.database;

import com.springboot.magicBooks.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDatabase extends JpaRepository<Admin, String> {
}
