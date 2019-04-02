package fr.zomet.tech.dto;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.time.LocalDate;

@JsonFilter("UserDto")
public class UserDto {

    private String id;

    @Hidden
    private LocalDate birthDate;

    @Hidden
    private String cardNumber;

    private String fullName;

    public String getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
