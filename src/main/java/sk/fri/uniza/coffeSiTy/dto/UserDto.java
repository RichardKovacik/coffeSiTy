package sk.fri.uniza.coffeSiTy.dto;
import org.springframework.format.annotation.DateTimeFormat;
import sk.fri.uniza.coffeSiTy.constants.CustomConstants;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

public class UserDto {
    private Long id;

    @NotEmpty
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Nickname musi obsahovat len pismena alebo cisla alebo ich kombinaciu")
    @Size(min = CustomConstants.MIN_LENGTH_OF_NICK, message = "Nickname musi obsahovat aspon 3 znaky")
    @Size(max = CustomConstants.MAX_LENGTH_OF_NICK, message = "Nickname musi obsahovat max 20 znakov")
    private String nick;

    @NotEmpty
    @NotNull
    @Size(min = 2, message = "Meno musi obsahovat aspon 2 znaky")
    @Size(max = 20, message = "Meno musi obsahovat max 20 znakov")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Meno musi obsahovat len pismena z abecedy")
    private String name;

    @NotEmpty
    @NotNull
    @Size(min = 2, message = "Priezvisko musi obsahovat aspon 3 znaky")
    @Size(max = 20, message = "Priezvisko musi obsahovat max 20 znakov")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Priezvisko musi obsahovat len pismena z abecedy")
    private String lastName;


    @NotNull
    @NotEmpty(message = "Email nemoze byt prazdny")
    @Size(min = 3, message = "Email musi obsahovat aspon 3 znaky")
    @Size(max = 30, message = "Email musi obsahovat max 30 znakov")
    @Email(message = "Neplatny format emailu")
    private String email;

    @NotEmpty
    @NotNull
    private String pass;

    @NotNull
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private Date birthdate;

    @Valid
    @NotNull
    private AddressDto addressDto;

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

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
