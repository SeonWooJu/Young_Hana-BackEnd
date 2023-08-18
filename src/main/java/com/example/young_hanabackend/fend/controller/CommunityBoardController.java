package com.example.young_hanabackend.fend.controller;

import com.example.young_hanabackend.entity.CommunityBoard;
import com.example.young_hanabackend.entity.CommunityBoardList;
import com.example.young_hanabackend.fend.service.CommunityBoardService;
import com.example.young_hanabackend.security.logic.JwtToken;
import com.example.young_hanabackend.security.model.FendResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/community")
public class CommunityBoardController {

    CommunityBoardService communityBoardService;
    JwtToken jwtToken;

    @Autowired
    public CommunityBoardController(CommunityBoardService communityBoardService, JwtToken jwtToken) {
        this.communityBoardService = communityBoardService;
        this.jwtToken = jwtToken;
    }

    @GetMapping("/board/list")
    public ResponseEntity<FendResponseObject<CommunityBoardList>> getCommunityBoardList(
            HttpServletRequest req,
            HttpServletResponse response,
            @RequestParam int topic,
            @RequestParam int limit_start,
            @RequestParam int limit_end
    ) {
        FendResponseObject<CommunityBoardList> ro = new FendResponseObject<>("Success");
        ro.setMessage("게시글 리스트");
        ro.setData(communityBoardService.getCommunityBoardList(topic, limit_start, limit_end));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<FendResponseObject<CommunityBoard>> getCommunityBoard(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam int board_no
    ) {
        FendResponseObject<CommunityBoard> ro = new FendResponseObject<>("Success");
        ro.setMessage("게시글 조회");
        ro.setData(communityBoardService.getCommunityBoard(board_no));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @PostMapping("/board")
    public ResponseEntity<FendResponseObject<Integer>> postCommunityBoard(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestBody CommunityBoard communityBoard
    ) {
        String token = jwtToken.resolveToken(req);
        FendResponseObject<Integer> ro = new FendResponseObject<>("Success");
        ro.setMessage("게시글 등록");

        if (!jwtToken.validateToken(token))
            return new ResponseEntity<>(ro, HttpStatus.FORBIDDEN);

        communityBoard.setStudent_no(jwtToken.getUserPk(token));
        ro.setData(communityBoardService.postCommunityBoard(communityBoard));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

//    @PutMapping("/board")
//    public ResponseEntity<FendResponseObject<Integer>>
}
