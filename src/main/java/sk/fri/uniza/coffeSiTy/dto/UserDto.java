package sk.fri.uniza.coffeSiTy.dto;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDto {
    private Long id;

    @NotEmpty
    private String nick;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastName;

    @NotEmpty(message = "Email nemoze byt prazdny")
    @Email
    private String email;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
