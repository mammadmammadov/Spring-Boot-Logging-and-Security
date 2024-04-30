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

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final DtoEntityMapper dtoEntityMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, DtoEntityMapper dtoEntityMapper) {
        this.bookRepository = bookRepository;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return dtoEntityMapper.convertEntityListToDtoList(books);
    }

    @Override
    public BookDto getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.map(dtoEntityMapper::convertEntityToDto).orElse(null);
    }

    @Override
    public void createBook(BookDto bookDto) {
        if (!SecurityUtils.isAdmin()) {
            throw new SecurityException("Unauthorized access: Admin privileges required");
        }
        Book book = dtoEntityMapper.convertDtoToEntity(bookDto);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookDto bookDto) {
        if (!SecurityUtils.isAdmin()) {
            throw new SecurityException("Unauthorized access: Admin privileges required");
        }
        Book bookToUpdate = dtoEntityMapper.convertDtoToEntity(bookDto);
        bookToUpdate.setId(id);
        bookRepository.save(bookToUpdate);
    }

    @Override
    public void deleteBook(Long id) {
        if (!SecurityUtils.isAdmin()) {
            throw new SecurityException("Unauthorized access: Admin privileges required");
        }
        bookRepository.deleteById(id);
    }

}
