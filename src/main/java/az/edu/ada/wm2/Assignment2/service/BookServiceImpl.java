package az.edu.ada.wm2.Assignment2.service;

import az.edu.ada.wm2.Assignment2.model.dto.BookDto;
import az.edu.ada.wm2.Assignment2.model.entity.Book;
import az.edu.ada.wm2.Assignment2.repo.BookRepository;
import az.edu.ada.wm2.Assignment2.util.DtoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return books.stream().map(dtoEntityMapper::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.map(dtoEntityMapper::convertEntityToDto).orElse(null);
    }

    @Override
    public void createBook(BookDto bookDto) {
        Book book = dtoEntityMapper.convertDtoToEntity(bookDto);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookDto bookDto) {
        Book bookToUpdate = dtoEntityMapper.convertDtoToEntity(bookDto);
        bookToUpdate.setId(id);
        bookRepository.save(bookToUpdate);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public String getGreetingMessage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            if (auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"))) {
                return "Welcome, Admin!";
            } else {
                return "Welcome, dear user!";
            }
        } else {
            return "Welcome!";
        }
    }

}
