package sk.fri.uniza.coffeSiTy.dto;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "Nickname musi obsahovat aspon 3 znaky")
    @Size(max = 20, message = "Nickname musi obsahovat max 20 znakov")
    private String nick;

    @NotEmpty
    @Size(min = 3, message = "Meno musi obsahovat aspon 3 znaky")
    @Size(max = 20, message = "Meno musi obsahovat max 20 znakov")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Meno musi obsahovat len pismena z abecedy")
    private String name;

    @NotEmpty
    @Size(min = 3, message = "Priezvisko musi obsahovat aspon 3 znaky")
    @Size(max = 20, message = "Priezvisko musi obsahovat max 20 znakov")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Priezvisko musi obsahovat len pismena z abecedy")
    private String lastName;

    @NotEmpty(message = "Email nemoze byt prazdny")
    @Size(min = 3, message = "Email musi obsahovat aspon 3 znaky")
    @Size(max = 20, message = "Email musi obsahovat max 20 znakov")
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
