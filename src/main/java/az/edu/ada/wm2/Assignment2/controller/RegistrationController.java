package az.edu.ada.wm2.Assignment2.controller;

import az.edu.ada.wm2.Assignment2.model.dto.SignupDto;
import az.edu.ada.wm2.Assignment2.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class handling user registration.
 */
@Controller
@RequestMapping("/signup")
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;

    /**
     * Displayinh the signup form.
     *
     * @param model Model to add attributes for the view.
     * @return View name "registration".
     */
    @GetMapping
    public String showSignup(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        logger.info("Showing signup form");
        return "registration";
    }

    /**
     * Handling user signup request.
     *
     * @param signupDto     SignupDto object containing user signup data.
     * @param bindingResult BindingResult for validation errors.
     * @param model         Model to add attributes for the view.
     * @return Redirects to login page if signup is successful, otherwise returns to the registration page.
     */
    @PostMapping
    public String signup(@Valid @ModelAttribute SignupDto signupDto, BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                logger.warn("Validation error(s) occurred during signup");
                return "registration";
            }

            if (userService.existsByUsername(signupDto.getUsername())) {
                model.addAttribute("error", "Username already exists. Please choose another username.");
                logger.warn("Attempted signup with existing username: {}", signupDto.getUsername());
                return "registration";
            }

            if (userService.existsByEmail(signupDto.getEmail())) {
                model.addAttribute("error", "Email address is already registered. Please use a different email.");
                logger.warn("Attempted signup with existing email: {}", signupDto.getEmail());
                return "registration";
            }

            userService.signup(signupDto);
            logger.info("User signed up successfully: {}", signupDto.getUsername());
            return "redirect:/login";
        } catch (Exception e) {
            logger.error("Error occurred during signup", e);
            model.addAttribute("error", "An unexpected error occurred. Please try again later.");
            return "registration";
        }
    }
}
