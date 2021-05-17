package com.smartx.dao;
import javax.validation.constraints.NotNull;

import com.smartx.entity.Author;

import edu.umd.cs.findbugs.annotations.NonNull;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.r2dbc.annotation.R2dbcRepository;
import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@R2dbcRepository(dialect = Dialect.MYSQL) 
public interface AuthorRepository extends ReactiveStreamsCrudRepository<Author, Long> {
    @NonNull
    @Override
    Mono<Author> findById(@NonNull @NotNull Long aLong); 

    @NonNull
    @Override
    Flux<Author> findAll();
}