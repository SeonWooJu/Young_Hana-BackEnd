package com.example.young_hanabackend.fend.controller;

import com.example.young_hanabackend.entity.CommunityComments;
import com.example.young_hanabackend.security.model.FendResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommunityCommentsController {

    public ResponseEntity<FendResponseObject<List<CommunityComments>>> getCommunityCommentsList(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam Integer board_no
    ) {

        return null;
    }

}
