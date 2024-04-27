package az.edu.ada.wm2.Assignment2.controller;

import az.edu.ada.wm2.Assignment2.model.dto.SignupDto;
import az.edu.ada.wm2.Assignment2.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class RegistrationController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String showSignup(Model model) {
        model.addAttribute("signupDto", new SignupDto());
        return "registration";
    }

    @PostMapping
    public String signup(@ModelAttribute SignupDto signupDto) {

        userRepo.save(signupDto.toUser(passwordEncoder));

        return "redirect:/login";
    }

}
