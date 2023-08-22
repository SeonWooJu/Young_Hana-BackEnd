package com.example.young_hanabackend.security.controller;

import com.example.young_hanabackend.entity.UserInfo;
import com.example.young_hanabackend.security.model.FendResponseObject;
import com.example.young_hanabackend.security.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/check-student-no")
    public ResponseEntity<FendResponseObject<Boolean>> checkStudentNo (
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestBody UserInfo user
    ) {
        FendResponseObject ro = new FendResponseObject("Success");
        ro.setMessage("학번 체크");
        ro.setData(accountService.checkStudentNo(user));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @PostMapping("/sing-up")
    public ResponseEntity<FendResponseObject<String>> singUp (
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestBody UserInfo user
    ) {
        FendResponseObject ro = new FendResponseObject("Success");
        ro.setMessage("회원가입");
        ro.setData(accountService.singUp(user));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @PostMapping("/sing-in")
    public ResponseEntity<FendResponseObject<String>> singIn (
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestBody UserInfo user
    ) {
        FendResponseObject ro = new FendResponseObject("Success");
        ro.setMessage("로그인");

        String token = accountService.singIn(user);
        if (token == null)
            return new ResponseEntity<>(ro, HttpStatus.BAD_REQUEST);

        ro.setData(token);

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }
}
