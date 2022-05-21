package com.dulich.dulich.form;

import lombok.Data;

@Data
public class SignupForm {
    private String username;
    private String password;
    private String rePassword;
    private String fullName;
    private String phone;
    private String email;
}
