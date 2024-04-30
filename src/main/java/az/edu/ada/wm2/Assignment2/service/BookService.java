package az.edu.ada.wm2.Assignment2.service;

import az.edu.ada.wm2.Assignment2.model.dto.BookDto;
import az.edu.ada.wm2.Assignment2.model.entity.Book;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    void createBook(BookDto bookDto);

    void updateBook(Long id, BookDto bookDto);

    void deleteBook(Long id);

}
