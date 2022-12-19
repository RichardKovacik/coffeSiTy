package sk.fri.uniza.coffeSiTy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.fri.uniza.coffeSiTy.dto.ArticleDto;
import sk.fri.uniza.coffeSiTy.entity.Article;
import sk.fri.uniza.coffeSiTy.repository.ArticleRepo;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleRepo articleRepo;
    public List<Article> getAllArticles() {
        return articleRepo.findAll();
    }

    public void saveArticle(ArticleDto articleDto) {
        Article article = new Article();
        article.setUser(articleDto.getUser());
        article.setContent(articleDto.getContent());
        article.setTitle(articleDto.getTitle());
        articleRepo.save(article);

    }
    public void updateArticle(ArticleDto articleDto) {
        Article article = this.findArticleById(articleDto.getId());
        if (article != null){
            article.setContent(articleDto.getContent());
            article.setTitle(articleDto.getTitle());
            this.articleRepo.save(article);
        }
    }
    public Article findArticleById(Long id){
        return articleRepo.findArticleById(id);
    }
    public void deleteArticleById(Long id){
         articleRepo.deleteArticleById(id);
    }
}
