package sk.fri.uniza.coffeSiTy.dto;
import org.springframework.format.annotation.DateTimeFormat;
import org.yaml.snakeyaml.scanner.Constant;
import sk.fri.uniza.coffeSiTy.constants.UserConstants;
import sk.fri.uniza.coffeSiTy.entity.Address;

import javax.persistence.Column;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

public class UserDto {
    private Long id;

    @NotEmpty
    @Size(min = UserConstants.MIN_LENGTH_OF_NICK, message = "Nickname musi obsahovat aspon 3 znaky")
    @Size(max = UserConstants.MAX_LENGTH_OF_NICK, message = "Nickname musi obsahovat max 20 znakov")
    private String nick;

    @NotEmpty
    @Size(min = 2, message = "Meno musi obsahovat aspon 3 znaky")
    @Size(max = 20, message = "Meno musi obsahovat max 20 znakov")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Meno musi obsahovat len pismena z abecedy")
    private String name;

    @NotEmpty
    @Size(min = 2, message = "Priezvisko musi obsahovat aspon 3 znaky")
    @Size(max = 20, message = "Priezvisko musi obsahovat max 20 znakov")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Priezvisko musi obsahovat len pismena z abecedy")
    private String lastName;

    @NotEmpty(message = "Email nemoze byt prazdny")
    @Size(min = 3, message = "Email musi obsahovat aspon 3 znaky")
    @Size(max = 30, message = "Email musi obsahovat max 30 znakov")
    @Email
    private String email;

    @NotEmpty
    private String pass;

    @NotNull
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private Date birthdate;

    private Address address;

    public UserDto() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
