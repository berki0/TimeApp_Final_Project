package com.example.TimeApp.Controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Password {
    @NotBlank
    @Size(min = 3,max = 15)
    private String password;
    @NotBlank
    @Size(min = 3,max = 15)
    private String verifyPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
