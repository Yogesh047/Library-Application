package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.BookDetails;
import com.example.library.service.BookService;

import java.util.List;

@RestController
public class BookController {

 @Autowired
 private BookService bookService;

	 @GetMapping("/getBook/{userType}")
	 public List<String> getBooks(@PathVariable("userType") String userType) {
	     return bookService.getBooks(userType);
	 }
	 
	 @PostMapping("/addBook")
	 public void addBook(@RequestBody BookDetails bookRequest) {
	     bookService.addBook(bookRequest);
	 }
	 
	 @DeleteMapping("/deleteBook/{bookName}")
	 public void deleteBook(@PathVariable("bookName") String bookName) {
	     bookService.deleteBook(bookName);
	 }
}
