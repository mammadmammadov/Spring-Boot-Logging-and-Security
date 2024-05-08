package az.edu.ada.wm2.Assignment2.service;

import az.edu.ada.wm2.Assignment2.model.dto.BookDto;
import az.edu.ada.wm2.Assignment2.model.entity.Book;
import az.edu.ada.wm2.Assignment2.repo.BookRepository;
import az.edu.ada.wm2.Assignment2.mapper.DtoEntityMapper;
import az.edu.ada.wm2.Assignment2.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the BookService interface providing methods to manage Book entities.
 */
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final DtoEntityMapper dtoEntityMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, DtoEntityMapper dtoEntityMapper) {
        this.bookRepository = bookRepository;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    /**
     * Retrieves a list of all books.
     *
     * @return List of BookDto objects representing all books.
     */
    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return dtoEntityMapper.convertEntityListToDtoList(books);
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id The ID of the book to retrieve.
     * @return BookDto representing the retrieved book, or null if not found.
     */
    @Override
    public BookDto getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.map(dtoEntityMapper::convertEntityToDto).orElse(null);
    }

    /**
     * Creates a new book.
     *
     * @param bookDto BookDto object containing data of the new book.
     */
    @Override
    public void createBook(BookDto bookDto) {
        if (!SecurityUtils.isAdmin()) {
            throw new SecurityException("Unauthorized access: Admin privileges required");
        }
        Book book = dtoEntityMapper.convertDtoToEntity(bookDto);
        bookRepository.save(book);
    }

    /**
     * Updates an existing book.
     *
     * @param id      ID of the book to update.
     * @param bookDto BookDto object containing updated data of the book.
     */
    @Override
    public void updateBook(Long id, BookDto bookDto) {
        if (!SecurityUtils.isAdmin()) {
            throw new SecurityException("Unauthorized access: Admin privileges required");
        }
        Book bookToUpdate = dtoEntityMapper.convertDtoToEntity(bookDto);
        bookToUpdate.setId(id);
        bookRepository.save(bookToUpdate);
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id The ID of the book to delete.
     */
    @Override
    public void deleteBook(Long id) {
        if (!SecurityUtils.isAdmin()) {
            throw new SecurityException("Unauthorized access: Admin privileges required");
        }
        bookRepository.deleteById(id);
    }

}
