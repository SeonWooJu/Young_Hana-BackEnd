package com.example.young_hanabackend.security.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class RegularExpression {

    public boolean studentNo (Integer student_no) {
        return Pattern.matches("^[0-9]*$", String.valueOf(String.valueOf(student_no)));
    }

    public boolean email (String email) {
        return Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+(.([a-zA-Z]{2,3}))?.[a-zA-Z]{2,3}$", email);
    }

    public boolean phoneNo (String phone_no) {
        return Pattern.matches("^010([0-9]{4})([0-9]{4})$", phone_no);
    }

    public boolean password (String pw) {
        return Pattern.matches("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&!])[A-Za-z\\d@#$%^&!]{10,20}$", pw);
    }
}
