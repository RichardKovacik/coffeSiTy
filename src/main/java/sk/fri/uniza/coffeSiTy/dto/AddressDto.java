package sk.fri.uniza.coffeSiTy.dto;

import sk.fri.uniza.coffeSiTy.constants.CustomConstants;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddressDto {
    @NotNull
    private Long cityId;

    @NotNull
    @NotEmpty(message = "PSC nemoze byt prazdne")
    @Pattern(regexp = "[0-9]+", message = "PSC musi obsahovat len cisla")
    @Size(min = CustomConstants.LENGTH_OF_PSC, max = CustomConstants.LENGTH_OF_PSC, message = "PSC musi obsahovat presne 5 cisel")
    private String psc;
    @NotNull(message = "Ulica nemoze byt prazdna")
    @NotEmpty(message = "Ulica nemoze byt prazdna")
    @Size(min = 4, message = "Nazov ulice musi obsahovat aspon 4 znaky")
    @Size(max = 30, message = "Nazov ulice musi obsahovat max 30 znakov")
    private String street;

    public AddressDto() {
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
