package az.edu.ada.wm2.Assignment2.controller;

import az.edu.ada.wm2.Assignment2.model.dto.BookDto;
import az.edu.ada.wm2.Assignment2.model.entity.Book;
import az.edu.ada.wm2.Assignment2.service.BookService;
import az.edu.ada.wm2.Assignment2.util.DtoEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;
    private final DtoEntityMapper dtoEntityMapper;

    public BookController(BookService bookService, DtoEntityMapper dtoEntityMapper) {
        this.bookService = bookService;
        this.dtoEntityMapper = dtoEntityMapper;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("isAdmin", isAdmin());
        return "books";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        if (!isAdmin()) {
            return "access-denied";
        }
        model.addAttribute("bookDto", new BookDto());
        return "create-book";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult bindingResult, Model model) {
        if (!isAdmin()) {
            return "access-denied";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            logger.warn("Attempted to create a book with invalid data: {}", bindingResult.getTarget());
            return "create-book";
        }
        try {
            Book book = dtoEntityMapper.convertDtoToEntity(bookDto);
            bookService.saveBook(book);
            logger.info("Book created successfully: {}", bookDto.getTitle());
            return "redirect:/books";
        } catch (Exception e) {
            logger.error("Error occurred while creating book", e);
            return "error";
        }
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        if (!isAdmin()) {
            return "redirect:/access-denied";
        }
        Book book = bookService.getBookById(id);
        BookDto bookDto = dtoEntityMapper.convertEntityToDto(book);
        model.addAttribute("bookDto", bookDto);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult bindingResult) {
        if (!isAdmin()) {
            return "access-denied";
        }
        if (bindingResult.hasErrors()) {
            logger.warn("Attempted to update a book with invalid data: {}", bindingResult.getTarget());
            return "update-book";
        }
        Book book = dtoEntityMapper.convertDtoToEntity(bookDto);
        book.setId(id);
        bookService.saveBook(book);
        logger.info("Book updated successfully: {}", book.getTitle());
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        if (!isAdmin()) {
            return "access-denied";
        }
        Book book = bookService.getBookById(id);
        if (book != null) {
            bookService.deleteBook(id);
            logger.info("Book deleted successfully: {}", book.getTitle());
        } else {
            logger.warn("Attempted to delete a non-existing book with ID: {}", id);
        }
        return "redirect:/books";
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    }
}
