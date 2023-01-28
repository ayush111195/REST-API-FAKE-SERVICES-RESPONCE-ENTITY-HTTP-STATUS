package com.springboot.restapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.entites.Book;
import com.springboot.restapi.services.Services;

//@org.springframework.stereotype.Controller
@RestController
public class Controller {

	@Autowired
	private Services service;

	@GetMapping("/test")

	// @RequestMapping(value = "/books",method = RequestMethod.GET)
	// @ResponseBody

	public String getTest() {
		return "Ayush";

	}

	@GetMapping("/book")
	public Book getBooks() {

		Book book = new Book();
		book.setAuthor("Ayush");
		book.setId(1111);
		book.setTitle("java");
		return book;

	}
//      @GetMapping("/books")
//    public List<Book>getbook(){
//    
//    	List<Book> allBook = this.service.getAllBook();
//		return allBook;

//    }

//	@GetMapping("/book/{id}")
//	public Book getBookById(@PathVariable("id") int id) {
//		Book bookById = this.service.getBookById(id);
//		return bookById;
//
//	}

//	@PostMapping("/addbook")
//	public Book addbook(@RequestBody Book book) { // @RequestBook aye hue deta (jeson ki form)ko Book ke object main
//													// store karwadegi
//		Book addBook = this.service.addBook(book);
//		return addBook;
//
//	}

//	@DeleteMapping("/dltbooks/{id}")
//	public void dltBook(@PathVariable("id") int boodid) {
//		this.service.dltBook(boodid);
//	}

//	@PutMapping("/putbooks/{id}")
//	public Book updateBook(@RequestBody Book book, @PathVariable("id") int id) {
//		this.service.updateBook(book, id);
//		return book;
//	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getbook() {

		List<Book> allBook = this.service.getAllBook();
		if (allBook.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(allBook));

	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
		Book bookById = this.service.getBookById(id);
		if(bookById!= null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(bookById));

	}

	@PostMapping("/addbook")
	public ResponseEntity<Book> addbook(@RequestBody Book book) { 
			try {
				Book addBook = this.service.addBook(book);
				return ResponseEntity.status(HttpStatus.CREATED).build();
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}										
	
		}
	
	@DeleteMapping("/dltbooks/{id}")
	public  ResponseEntity<Void> dltBook(@PathVariable("id") int boodid) {
		try {
			this.service.dltBook(boodid);
			return  ResponseEntity.ok().build();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	
	}

	@PutMapping("/putbooks/{id}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id) {
		
		try {
			this.service.updateBook(book, id);
            return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	
	}
}

// Rest main hum controller ki jgh @RestController  ka use  kiya jat h @RestController ke sath 
//responcebody lgane ki jarurt nhi padti....

//@RequestMapping(value = "/books",method = RequestMethod.GET) main humko method btana padta h isleye hum directly 
//@GetMapping ka use kar skte h.... 
