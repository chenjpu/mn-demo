package com.smartx.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import com.smartx.dao.AuthorRepository;
import com.smartx.dao.BookRepository;
import com.smartx.entity.Author;
import com.smartx.entity.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Singleton
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) { // <1>
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional // <2>
    @PostConstruct
    public Mono<Void> setupData() {
        return Mono.from(authorRepository.save(new Author("Stephen King")))
                .flatMapMany((author -> bookRepository.saveAll(Arrays.asList(
                        new Book("The Stand", 1000, author),
                        new Book("The Shining", 400, author)
                ))))
                .then(Mono.from(authorRepository.save(new Author("James Patterson"))))
                .flatMapMany((author ->
                        bookRepository.save(new Book("Along Came a Spider", 300, author))
                )).then();
    }
    
    @Transactional // <2>
    public Flux<Book> findAll(){
    	return bookRepository.findAll();
    }
}
