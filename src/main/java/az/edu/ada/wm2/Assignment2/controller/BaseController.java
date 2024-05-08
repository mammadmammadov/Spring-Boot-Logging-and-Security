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

    /**
     * handling GET requests to paths "/welcome", "/users", and "/admins".
     * rendering the welcome page.
     *
     * @param model The Model object to convey data to the view.
     * @return The name of the view template to render ("welcome").
     */
    @GetMapping({"/welcome","/users", "/admins"})
    public String getWelcomePage(Model model) {
        return "welcome";
    }

    /**
     * handling GET requests to the path "/bye".
     * rendering the farewell page with a farewell message.
     *
     * @param model The Model object to convey data to the view.
     * @return The name of the view template to render ("welcome").
     */
    @GetMapping("/bye")
    public String getFarewellPage(Model model) {
        model.addAttribute("message", "Goodbye!"); // adding a farewell message to the model
        return "welcome"; // rendering the "welcome" view
    }
}
