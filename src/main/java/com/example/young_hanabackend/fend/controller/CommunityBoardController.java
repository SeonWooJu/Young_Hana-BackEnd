package com.example.young_hanabackend.fend.controller;

import com.example.young_hanabackend.entity.CommunityBoard;
import com.example.young_hanabackend.entity.CommunityBoardList;
import com.example.young_hanabackend.fend.service.CommunityBoardService;
import com.example.young_hanabackend.security.model.FendResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/community")
public class CommunityBoardController {

    CommunityBoardService communityBoardService;

    @Autowired
    public CommunityBoardController(CommunityBoardService communityBoardService) {
        this.communityBoardService = communityBoardService;
    }

    @GetMapping("/board/list")
    public ResponseEntity<FendResponseObject<CommunityBoardList>> getCommunityBoardList(
            HttpServletRequest req,
            HttpServletResponse response,
            @RequestParam int kind,
            @RequestParam int limit_start,
            @RequestParam int limit_end
    ) {
        FendResponseObject<CommunityBoardList> ro = new FendResponseObject<>("Success");
        ro.setMessage("게시판 리스트");
        ro.setData(communityBoardService.getCommunityBoardList(kind, limit_start, limit_end));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<FendResponseObject<CommunityBoard>> getCommunityBoard(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam int board_no
    ) {
        FendResponseObject<CommunityBoard> ro = new FendResponseObject<>();
        ro.setMessage("게시판");
        ro.setData(communityBoardService.getCommunityBoard(board_no));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }
}
