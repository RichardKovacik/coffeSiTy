package sk.fri.uniza.coffeSiTy.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressDto {
    @NotNull
    private Long cityId;

    @NotNull
    @NotEmpty(message = "PSC nemoze byt prazdne")
    private String psc;
    @NotNull
    @NotEmpty(message = "Ulica nemoze byt prazdna")
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
