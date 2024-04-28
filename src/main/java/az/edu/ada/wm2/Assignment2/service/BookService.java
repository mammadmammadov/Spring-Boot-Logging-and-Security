package az.edu.ada.wm2.Assignment2.service;

import az.edu.ada.wm2.Assignment2.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book saveBook(Book book);
    void deleteBook(Long id);
}
