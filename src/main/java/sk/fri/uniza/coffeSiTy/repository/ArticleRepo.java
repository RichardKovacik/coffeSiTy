package sk.fri.uniza.coffeSiTy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.fri.uniza.coffeSiTy.entity.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {
}