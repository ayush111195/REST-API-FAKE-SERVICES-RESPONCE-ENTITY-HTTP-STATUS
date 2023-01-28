package com.springboot.restapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.restapi.entites.Book;

// This is services leary to connect with database but here we make a fake service ---->>>

@Service
public class Services {
  
	private static List<Book> list=new ArrayList<>();
	
	static {
		list.add(new Book(1111,"Arpit","This Is All About Bussnices"));
		list.add(new Book(1112,"Akash","This Is All About codeing"));
		list.add(new Book(1113,"Shivam","This Is All About love"));
	}
	
	//get all book--->>
	
	public List<Book>getAllBook(){
		
		return list;
		
	}
	
	//get single book by id---->>>
	
	public  Book getBookById(int id){
		
		Book book=null;
		try {
			book=list.stream().filter(e->e.getId()==id).findFirst().get();

		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return book;
		
	}
	
	//  Add the book---->>>
	
public Book addBook(Book b) {
	list.add(b);
	return b;
}

    // delete the book by id---->>>

public void dltBook(int bid) {
	list.stream().filter(e->e.getId()!=bid).collect(Collectors.toList());
	
}

   // update the book--->>
public void updateBook(Book book,int id) {
list=list.stream().map(e->{
		if(e.getId()==id) {
			e.setTitle(book.getTitle());
			e.setAuthor(book.getAuthor());
			
		}
		return e;
		}).collect(Collectors.toList());
	
	
}
}
