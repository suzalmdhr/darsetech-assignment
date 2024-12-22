package com.example.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BookDao;
import com.example.entity.Book;

@Service
public class BookServImpl implements BookServ{
	
	@Autowired
	private BookDao bookDao;

	@Override
	public Book addBook(Book book) {
		Book saved = this.bookDao.save(book);
		return saved;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> allBooks = this.bookDao.findAll();
		return allBooks;
	}

	@Override
	public Book updateBook(String isbn, String name) {
		Book gotBook = this.bookDao.findById(isbn).orElseThrow(() -> new RuntimeException("No such book"));
		gotBook.setName(name);
		bookDao.save(gotBook);
		return gotBook;
	}

	@Override
	public void delBook(String isbn) {
		Book delBook = this.bookDao.findById(isbn).orElseThrow(() -> new RuntimeException("No such book"));
		this.bookDao.delete(delBook);
		
	}

	@Override
	public Book getBook(String isbn) {
		Book book = this.bookDao.findById(isbn).orElseThrow(() -> new RuntimeException("No such book"));
		return book;
	}

}
