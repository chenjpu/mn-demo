package com.smartx.ui;

import com.smartx.dao.BookRepository;
import com.smartx.entity.Book;
import com.smartx.service.AuthorService;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller("/books")
public class BookController {
	private final BookRepository bookRepository;
	private final AuthorService authorService;

	public BookController(BookRepository bookRepository,AuthorService authorService) {
		this.bookRepository = bookRepository;
		this.authorService = authorService;
	}

	// tag::read[]
	@Get("/tx")
	Flux<Book> Tx() {
		return authorService.findAll(); // <1>
	}
	
	@Get("/ntx")
	Flux<Book> notTx() {
		return bookRepository.findAll(); // <1>
	}
	
	@Get("/st")
	Mono<String> st() {
		return Mono.just("test"); // <1>
	}
	
}
