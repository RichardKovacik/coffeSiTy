package sk.fri.uniza.coffeSiTy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sk.fri.uniza.coffeSiTy.controllerHelper.ControllerHelper;
import sk.fri.uniza.coffeSiTy.dto.UserDto;
import sk.fri.uniza.coffeSiTy.entity.Address;
import sk.fri.uniza.coffeSiTy.entity.Article;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.service.ArticleService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/blog")
    @Transactional
    public String getAllArticles(Model model) {
        List<Article> listArticles = articleService.getAllArticles();
        model.addAttribute("list", listArticles);
        return "blog";
    }
    @RequestMapping("/addArticle")
    public String getPageForAddArticle(Model model) {
        //todo: do buducna prerobit article na dto object
        model.addAttribute("article", new Article());
        return "addArticle";
    }

    @PostMapping("/addArticle/save")
    @Transactional
    public String saveArticle(@ModelAttribute("article") Article article,
                               BindingResult result,
                               Model model) {


        //ulozim article s danym autorom kotry pridal slanok
        articleService.saveArticle(article);

        return "redirect:/addArticle?success";
    }
}
