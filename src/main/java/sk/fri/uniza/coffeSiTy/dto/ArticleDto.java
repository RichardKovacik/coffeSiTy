package sk.fri.uniza.coffeSiTy.dto;

import sk.fri.uniza.coffeSiTy.entity.User;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ArticleDto {

    Long id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 60, message = "Napdis clanku musi obsahovat min 3 a max 60 znakov")
    private String title;

    @NotNull
    @NotEmpty
    @Size(min = 100, max = 2000, message ="Obsah clanku musi obsahovat min 100 znakov a max 2000 znakov")
    private String content;

    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
