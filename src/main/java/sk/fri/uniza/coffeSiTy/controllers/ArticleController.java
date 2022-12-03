package sk.fri.uniza.coffeSiTy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.fri.uniza.coffeSiTy.entity.Article;
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
        //todo: validacie
        //ulozim article s danym autorom kotry pridal slanok
        articleService.saveArticle(article);
        return "redirect:/addArticle?success";
    }

    @Transactional
    @GetMapping("/blog/view/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             Model model,
                             RedirectAttributes ra) {
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "article";
    }
}
