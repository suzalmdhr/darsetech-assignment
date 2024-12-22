package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Book;

public interface BookDao extends JpaRepository<Book, String>{

}
