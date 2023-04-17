package com.springboot.magicBooks.database;

import com.springboot.magicBooks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserDatabase extends JpaRepository<User, String> {
}
