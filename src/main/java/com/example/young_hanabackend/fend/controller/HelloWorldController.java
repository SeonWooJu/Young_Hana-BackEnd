package com.example.young_hanabackend.fend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {

    @GetMapping("/helloworld")
    public ResponseEntity<String> getHelloWord(
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        return new ResponseEntity<>("HelloWorld Get", HttpStatus.OK);
    }

    @PostMapping("/helloworld")
    public ResponseEntity<String> postHelloWord(
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        return new ResponseEntity<>("HelloWorld Post", HttpStatus.OK);
    }

    @PutMapping("/helloworld")
    public ResponseEntity<String> putHelloWord(
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        return new ResponseEntity<>("HelloWorld Put", HttpStatus.OK);
    }

    @DeleteMapping("/helloworld")
    public ResponseEntity<String> deleteHelloWord(
            HttpServletRequest req,
            HttpServletResponse res
    ) {
        return new ResponseEntity<>("HelloWorld Delete", HttpStatus.OK);
    }
}
