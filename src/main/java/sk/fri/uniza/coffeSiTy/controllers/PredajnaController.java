package sk.fri.uniza.coffeSiTy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PredajnaController {
    @RequestMapping("/predajna")
    public String defectDetails() {
        return "predajna";
    }
}
