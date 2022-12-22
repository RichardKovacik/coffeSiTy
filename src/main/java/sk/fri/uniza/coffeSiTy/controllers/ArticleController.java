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
import sk.fri.uniza.coffeSiTy.dto.ArticleDto;
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
    public String getAllArticles(Model model, String idOfSelectedUser) {
        //vytiahnem z db aktualne prihlseneho usera
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNick(auth.getName());
        model.addAttribute("user",user);
        List<User> listOfUsers = userService.getUsersWhoHaveArticles();
        model.addAttribute("users",listOfUsers);
        //zobrazenie zoznamu articlov na zaklade zvoleneho uzivatela
        List<Article> listOfArticles = idOfSelectedUser != null ?
                articleService.getArtcilesByAuthor(idOfSelectedUser) : articleService.getAllArticles();
        model.addAttribute("list", listOfArticles);
        return "blog";
    }
    @RequestMapping("/addArticle")
    public String getPageForAddArticle(Model model) {
        model.addAttribute("article", new ArticleDto());
        return "addArticle";
    }

    @PostMapping("/addArticle/save")
    @Transactional
    public String saveArticle(@Valid @ModelAttribute("article") ArticleDto articleDto,
                               BindingResult result,
                               Model model) {
        //validacie spravnosti clanku
        if (result.hasErrors()) {
            model.addAttribute("article", articleDto);
            System.out.println("chyba pridavani articla");
            return "/addArticle";
        }

        //ulozim article s danym autorom kotry pridal clanok(moze len prihlaseny uzivatel)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByNick(auth.getName());
        if (user != null) {
            articleDto.setUser(user);
        }
        articleService.saveArticle(articleDto);
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
    @GetMapping("/blog/edit/{id}")
    public String showEditArticlePage(@PathVariable("id") Long id,
                              Model model,
                              RedirectAttributes ra) {
        Article article = articleService.findArticleById(id);
        ArticleDto articleDto = new ArticleDto();
        if (article != null) {
            articleDto.setId(article.getId());
            articleDto.setUser(article.getUser());
            articleDto.setTitle(article.getTitle());
            articleDto.setContent(article.getContent());
        }
        model.addAttribute("article", articleDto);
        return "editArticle";
    }

    @PostMapping("/editArticle/save")
    @Transactional
    public String aveEditedArticle(@Valid @ModelAttribute("article") ArticleDto articleDto,
                              BindingResult result,
                              Model model) {
        //validacie spravnosti clanku
        if (result.hasErrors()) {
            return "/editArticle";
        }

        System.out.println(articleDto.getContent().length());
        System.out.println(articleDto.getTitle().length());
        articleService.updateArticle(articleDto);
        Long id = articleDto.getId();
        return "redirect:/blog/edit/"+id+"?success";
    }

    @Transactional
    @GetMapping("/blog/delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id,
                             Model model,
                             RedirectAttributes ra) {
            articleService.deleteArticleById(id);
            //znova nacitam uzivatelov ale uz bez vymazeneho
            List<Article> list = articleService.getAllArticles();
            model.addAttribute("list", list);
        return "redirect:/blog?success";
    }
}
