package com.hkarabakla.controller;

import com.hkarabakla.model.Book;
import com.hkarabakla.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getAllBooks(Optional<Integer> page, Optional<Integer> size) {

        if(!page.isPresent()) {
            page = Optional.of(0);
        }
        if(!size.isPresent()) {
            size = Optional.of(10);
        }
        Page<Book> bookList = bookRepository.findAll(new PageRequest(page.get(), size.get()));
        return new ResponseEntity<List<Book>>(bookList.getContent(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getById(@PathVariable String id) {

        if(id == null) {
            return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
        }

        Book book = bookRepository.findOne(id);

        if(book == null) {
            return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/isbn/{isbn}", method = RequestMethod.GET)
    public ResponseEntity<Book> getAllBooks(@PathVariable String isbn) {

        if(isbn == null) {
            return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
        }

        Book book = bookRepository.findByIsbn(isbn);

        if(book == null) {
            return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> getByName(@PathVariable String name) {

        if(name == null) {
            return new ResponseEntity<List<Book>>(HttpStatus.BAD_REQUEST);
        }

        List<Book> bookList = bookRepository.findByNameContaining(name);

        if(bookList == null) {
            return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Book> createNewBook(@RequestBody Book book) {

        if(book == null || !isBookValid(book)) {
            return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
        }

        book = bookRepository.save(book);

        if(book == null) {
            return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@RequestBody Book book) {

        if(book == null || !isBookValid(book)) {
            return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
        }

        book = bookRepository.save(book);

        if(book == null) {
            return new ResponseEntity<Book>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    private boolean isBookValid(Book book) {

        if(book.getName() == null && book.getIsbn() == null && book.getPrice() == null)
        {
            return false;
        }

        return true;
    }
}
