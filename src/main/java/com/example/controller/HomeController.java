package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Book;
import com.example.service.BookServ;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@RequestMapping("/book")
@Tag(name = "Book Controller", description = "Controller for managing books")
public class HomeController {
	
	@Autowired
	private BookServ bookServ;
	
	 @Operation(summary = "Homepage", description = "Displays the homepage")
	@GetMapping
	public String index(Model model) {
	
		return "index";
	}
	
	 @Operation(summary = "Form to add a new book", description = "Displays a form to add a new book")
	@GetMapping("/form")
	public String addBook(Model model) {
		model.addAttribute("book",new Book());
		return "add"; 
	}
	
	 @Operation(summary = "Add a new book", description = "Adds a new book to the system")
	@PostMapping("/add")
	public String addBook(@ModelAttribute Book book) {
		this.bookServ.addBook(book);
		return "redirect:/book/form";
	}
	
	  @Operation(summary = "List all books", description = "Displays a list of all books")
	@GetMapping("/lists")
	public String listBooks(Model model) {
		model.addAttribute("books",this.bookServ.getAllBooks());
		return "list";
		
	}
	
	  @Operation(summary = "Edit book details", description = "Displays a form to edit a book's details")
	@GetMapping("/edit/{id}")
	public String editBookForm(@PathVariable ("id") String id,Model model) {
		
		model.addAttribute("book",this.bookServ.getBook(id));
		return "edit";
	}
	
	  @Operation(summary = "Save edited book details", description = "Saves the edited book details")
	@PostMapping("/edit/form")
	public String edit(@ModelAttribute Book book) {
		this.bookServ.updateBook(book.getIsbn(), book.getName());
		return "redirect:/book/lists";
		
	}
	  
	  @Operation(summary = "Delete a book", description = "Deletes a book by its ID")
	@GetMapping("/delete/{id}")
	public String delBook(@PathVariable("id") String isbn) {
		this.bookServ.delBook(isbn);
		return "redirect:/book/lists";
	}
	
	

}
