package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.entity.Article;
import sk.fri.uniza.coffeSiTy.entity.User;
import sk.fri.uniza.coffeSiTy.repository.ArticleRepo;
import sk.fri.uniza.coffeSiTy.repository.UserRepo;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepo articleRepo;

    @Autowired
    UserRepo userRepo;

    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    public void saveArticle(Article article) {
        //zistim usernam aktualne prihlaseneho uzivatela
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findUserByNick(auth.getName());
        if (user != null){
            article.setUser(user);
        }
        articleRepo.save(article);

    }
}
