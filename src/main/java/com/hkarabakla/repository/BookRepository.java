package com.hkarabakla.repository;

import com.hkarabakla.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface BookRepository extends PagingAndSortingRepository<Book, String> {

    List<Book> findByNameContaining(String name);
    Book findByIsbn(String isbn);
}
