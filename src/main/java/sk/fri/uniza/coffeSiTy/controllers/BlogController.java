package sk.fri.uniza.coffeSiTy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.fri.uniza.coffeSiTy.entity.Article;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.service.ArticleService;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/blog")
    @Transactional
    public String getAllArticles(Model model) {
        List<Article> listArticles = articleService.getAllArticles();
        model.addAttribute("list", listArticles);
        return "blog";
    }
}
