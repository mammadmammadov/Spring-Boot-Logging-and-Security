package az.edu.ada.wm2.Assignment2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BusinessController {

    @GetMapping("/users")
    public String getUsersHomePage(Model model){
        model.addAttribute("message", "This is home for USERS");
        return "welcome";
    }


    @GetMapping("/admins")
    public String getAdminsHomePage(Model model){
        model.addAttribute("message", "This is home for ADMINS");
        return "welcome";
    }
}
