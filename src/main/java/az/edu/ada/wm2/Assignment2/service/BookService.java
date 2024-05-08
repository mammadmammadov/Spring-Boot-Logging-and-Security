package az.edu.ada.wm2.Assignment2.service;

import az.edu.ada.wm2.Assignment2.model.dto.BookDto;
import az.edu.ada.wm2.Assignment2.model.entity.Book;

import java.util.List;

/**
 * Service interface for managing Book entities.
 */
public interface BookService {

    /**
     * Retrieves a list of all books.
     *
     * @return List of BookDto objects representing all books.
     */
    List<BookDto> getAllBooks();

    /**
     * Retrieves a book by its ID.
     *
     * @param id The ID of the book to retrieve.
     * @return BookDto representing the retrieved book, or null if not found.
     */
    BookDto getBookById(Long id);

    /**
     * Creates a new book.
     *
     * @param bookDto BookDto object containing data of the new book.
     */
    void createBook(BookDto bookDto);

    /**
     * Updates an existing book.
     *
     * @param id      ID of the book to update.
     * @param bookDto BookDto object containing updated data of the book.
     */
    void updateBook(Long id, BookDto bookDto);

    /**
     * Deletes a book by its ID.
     *
     * @param id The ID of the book to delete.
     */
    void deleteBook(Long id);
}
