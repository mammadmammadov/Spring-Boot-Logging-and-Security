package az.edu.ada.wm2.Assignment2.controller;

import az.edu.ada.wm2.Assignment2.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

    private final BookService bookService;

    public BaseController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"/welcome","/users", "/admins"})
    public String getWelcomePage(Model model) {
        String greetingMessage = bookService.getGreetingMessage();
        model.addAttribute("greetingMessage", greetingMessage);
        return "welcome";
    }

    @GetMapping("/bye")
    public String getFarewellPage(Model model) {
        model.addAttribute("message", "Goodbye!");
        return "welcome";
    }
}
