package sk.fri.uniza.coffeSiTy.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    @RequestMapping("/profil")
    public String home() {
        return "profile";
    }
}
