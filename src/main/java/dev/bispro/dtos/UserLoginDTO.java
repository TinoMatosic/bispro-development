package dev.bispro.dtos;

import dev.bispro.domain.Email;
import dev.bispro.domain.Plan;
import dev.bispro.domain.Role;

public class UserLoginDTO {

    private Email email;

    private String password;

    public UserLoginDTO() {
    }

    public UserLoginDTO(Email email, String password) {
        this.email = email;
        this.password = password;
    }

    public Email getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

// https://dzone.com/articles/how-to-use-cookies-in-spring-boot
// browser Cookies zur Übergabe des aktuellen Benutzers (Tipp von Tim)