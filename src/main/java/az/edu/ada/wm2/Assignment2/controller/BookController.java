package az.edu.ada.wm2.Assignment2.controller;

import az.edu.ada.wm2.Assignment2.model.dto.BookDto;
import az.edu.ada.wm2.Assignment2.service.BookService;
import az.edu.ada.wm2.Assignment2.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        List<BookDto> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("isAdmin", SecurityUtils.isAdmin());
        return "books";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("bookDto", new BookDto());
        return "create-book";
    }

    @PostMapping("/create")
    public String createBook(@Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            logger.warn("Attempted to create a book with invalid data: {}", bindingResult.getTarget());
            return "create-book";
        }
        try {
            bookService.createBook(bookDto);
            logger.info("Book created successfully: {}", bookDto.getTitle());
            return "redirect:/books";
        } catch (Exception e) {
            logger.error("Error occurred while creating book", e);
            return "error";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        BookDto bookDto = bookService.getBookById(id);
        model.addAttribute("bookDto", bookDto);
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @Valid @ModelAttribute("bookDto") BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.warn("Attempted to update a book with invalid data: {}", bindingResult.getTarget());
            return "update-book";
        }
        try {
            bookService.updateBook(id, bookDto);
            logger.info("Book updated successfully: {}", bookDto.getTitle());
            return "redirect:/books";
        } catch (Exception e) {
            logger.error("Error occurred while updating book", e);
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        BookDto bookDto = bookService.getBookById(id);
        if (bookDto != null) {
            bookService.deleteBook(id);
            logger.info("Book deleted successfully: {}", bookDto.getTitle());
        } else {
            logger.warn("Attempted to delete a non-existing book with ID: {}", id);
        }
        return "redirect:/books";
    }
}
