package com.example.service;

import java.util.List;

import com.example.entity.Book;

public interface BookServ {
	
	Book addBook(Book book);
	
	List<Book> getAllBooks();
	
	Book updateBook(String isbn,String name);
	
	void delBook(String isbn);
	
	Book getBook(String isbn);

}
