package sk.fri.uniza.coffeSiTy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.fri.uniza.coffeSiTy.entity.Article;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.exception.UserNotFoundException;
import sk.fri.uniza.coffeSiTy.service.ArticleService;
import sk.fri.uniza.coffeSiTy.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @RequestMapping("/blog")
    @Transactional
    public String getAllArticles(Model model) {
        List<Article> listArticles = articleService.getAllArticles();
        model.addAttribute("list", listArticles);
        //vytiahnem z db aktualne prihlseneho usera
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNick(auth.getName());
        model.addAttribute("user",user);
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
        //ulozim article s danym autorom kotry pridal clanok(moze len prihlaseny uzivatel)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNick(auth.getName());
        if (user != null) {
            article.setUser(user);
        }
        articleService.saveArticle(article);
        return "redirect:/addArticle?success";
    }

    @Transactional
    @GetMapping("/blog/view/{id}")
    public String showArticle(@PathVariable("id") Long id,
                             Model model,
                             RedirectAttributes ra) {
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "article";
    }

    @Transactional
    @GetMapping("/blog/delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id,
                             Model model,
                             RedirectAttributes ra) {
        //todo: validacia nespravneho id
            articleService.deleteArticleById(id);
            //znova nacitam uzivatelov ale uz bez vymazeneho
            List<Article> list = articleService.getAllArticles();
            model.addAttribute("list", list);
        return "redirect:/blog?success";
    }
}
