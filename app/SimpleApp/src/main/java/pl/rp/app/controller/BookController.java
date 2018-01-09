package pl.rp.app.controller;

import pl.rp.app.exception.BookIdMismatchException;
import pl.rp.app.exception.BookNotFoundException;
import pl.rp.app.model.Book;
import pl.rp.app.model.BookRepository;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
 
    @Autowired
    private BookRepository bookRepository;
 
    @GetMapping
    public Iterable<Book> findAll() {
    	TestController.traceCounter(3, "BookController findAll()");
        return bookRepository.findAll();
    }
 
    @GetMapping("/title/{bookTitle}")
    public List<Book> findByTitle(@PathVariable String bookTitle) {
    	TestController.traceCounter(3, "BookController findByTitle(" + bookTitle + ")");
        return bookRepository.findByTitle(bookTitle);
    }
 
    @GetMapping("/{id}")
    public Book findOne(@PathVariable long id) {
    	TestController.traceCounter(3, "BookController findOne(" + id + ")");
        return bookRepository.findOne(id).orElseThrow(BookNotFoundException::new);
    }
 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
    	TestController.traceCounter(3, "BookController create(" + book + ")");
        return bookRepository.save(book);
    }
 
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
    	TestController.traceCounter(3, "BookController delete(" + id + ")");
        bookRepository.findOne(id).orElseThrow(BookNotFoundException::new);
        bookRepository.delete(id);
    }
 
    @PutMapping("/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable long id) {
    	TestController.traceCounter(1, "SimpleController updateBook(" + book + ", " + id + ")");
        if (book.getId() != id) {
          throw new BookIdMismatchException();
        }
        bookRepository.findOne(id).orElseThrow(BookNotFoundException::new);
        TestController.traceCounter(2, "SimpleController updateBook(" + book + ", " + id + ")");
        return bookRepository.save(book);
    }
}
