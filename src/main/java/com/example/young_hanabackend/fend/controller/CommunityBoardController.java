package com.example.young_hanabackend.fend.controller;

import com.example.young_hanabackend.entity.CommunityBoard;
import com.example.young_hanabackend.fend.service.CommunityBoardService;
import com.example.young_hanabackend.security.util.JwtToken;
import com.example.young_hanabackend.security.model.FendResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<FendResponseObject<List<CommunityBoard>>> getCommunityBoardList(
            HttpServletRequest req,
            HttpServletResponse response,
            @RequestParam Integer topic,
            @RequestParam Integer limit_start,
            @RequestParam Integer limit_end
    ) {
        FendResponseObject<List<CommunityBoard>> ro = new FendResponseObject<>("Success");
        ro.setMessage("게시글 리스트");
        ro.setData(communityBoardService.getCommunityBoardList(topic, limit_start, limit_end));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @GetMapping("/board")
    public ResponseEntity<FendResponseObject<CommunityBoard>> getCommunityBoard(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam Integer board_no
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

    @PutMapping("/board")
    public ResponseEntity<FendResponseObject<Integer>> putCommunityBoard(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestBody CommunityBoard communityBoard
    ) {
        String token = jwtToken.resolveToken(req);
        FendResponseObject<Integer> ro = new FendResponseObject<>("Success");
        ro.setMessage("게시글 수정");

        communityBoard.setStudent_no(jwtToken.getUserPk(token));

        // jwt의 유효성 체크 및 자신의 게시물인지 확인
        if (!jwtToken.validateToken(token) || !communityBoardService.checkMyCommunityBoard(communityBoard.getStudent_no(), communityBoard.getCB_no()))
            return new ResponseEntity<>(ro, HttpStatus.FORBIDDEN);

        ro.setData(communityBoardService.putCommunityBoard(communityBoard));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @DeleteMapping("/board")
    public ResponseEntity<FendResponseObject<Integer>> deleteCommunityBoard (
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam Integer board_no
    ) {
        String token = jwtToken.resolveToken(req);
        FendResponseObject<Integer> ro = new FendResponseObject<>("Success");
        ro.setMessage("게시글 삭제");

        // jwt의 유효성 체크 및 자신의 게시물인지 확인
        if (!jwtToken.validateToken(token) || !communityBoardService.checkMyCommunityBoard(jwtToken.getUserPk(token), board_no))
            return new ResponseEntity<>(ro, HttpStatus.FORBIDDEN);

        ro.setData(communityBoardService.deleteCommunityBoard(board_no));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @GetMapping("/board/check")
    public ResponseEntity<FendResponseObject<Boolean>> checkMyCommunityBoard(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam Integer board_no
    ) {
        String token = jwtToken.resolveToken(req);
        FendResponseObject<Boolean> ro = new FendResponseObject<>("Success");
        ro.setMessage("게시글 체크");

        // jwt의 유효성 체크
        if (!jwtToken.validateToken(token))
            return new ResponseEntity<>(ro, HttpStatus.FORBIDDEN);

        ro.setData(communityBoardService.checkMyCommunityBoard(jwtToken.getUserPk(token), board_no));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }
}
