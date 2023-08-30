package com.example.young_hanabackend.security.controller;

import com.example.young_hanabackend.entity.UserInfo;
import com.example.young_hanabackend.security.model.FendResponseObject;
import com.example.young_hanabackend.security.service.AccountService;
import com.example.young_hanabackend.security.util.JwtToken;
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
    JwtToken jwtToken;

    @Autowired
    public AccountController(AccountService accountService, JwtToken jwtToken) {
        this.accountService = accountService;
        this.jwtToken = jwtToken;
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

    @GetMapping("/check-role-admin")
    public ResponseEntity<FendResponseObject<Boolean>> checkRoleAdmin (
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        String token = jwtToken.resolveToken(req);

        FendResponseObject ro = new FendResponseObject("Success");
        ro.setMessage("어드민 체크");
        ro.setData(jwtToken.checkAdminRole(token));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @PostMapping("/sing-up")
    public ResponseEntity<FendResponseObject<Integer>> singUp (
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestBody UserInfo user
    ) {
        FendResponseObject<Integer> ro = new FendResponseObject("Success");
        ro.setMessage("회원가입");

        Integer student_no = accountService.singUp(user);

        if (student_no == null)
            return new ResponseEntity<>(ro, HttpStatus.BAD_REQUEST);

        ro.setData(student_no);

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
