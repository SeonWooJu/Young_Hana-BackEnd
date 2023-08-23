package com.example.young_hanabackend.fend.controller;

import com.example.young_hanabackend.entity.CommunityComment;
import com.example.young_hanabackend.fend.service.CommunityCommentService;
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
public class CommunityCommentController {

    CommunityCommentService communityCommentService;
    JwtToken jwtToken;

    @Autowired
    public CommunityCommentController(CommunityCommentService communityCommentService, JwtToken jwtToken) {
        this.communityCommentService = communityCommentService;
        this.jwtToken = jwtToken;
    }

    @GetMapping("/comments")
    public ResponseEntity<FendResponseObject<List<CommunityComment>>> getCommunityCommentList(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam Integer board_no
    ) {
        FendResponseObject<List<CommunityComment>> ro = new FendResponseObject<>("Success");
        ro.setMessage("댓글 리스트");
        ro.setData(communityCommentService.getCommunityCommentList(board_no));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<FendResponseObject<Integer>> postCommunityComment(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestBody CommunityComment communityComment
    ) {
        String token = jwtToken.resolveToken(req);
        FendResponseObject<Integer> ro = new FendResponseObject<>("Success");
        ro.setMessage("댓글 쓰기");

        // jwt 유효성 검증
        if (!jwtToken.validateToken(token))
            return new ResponseEntity<>(ro, HttpStatus.FORBIDDEN);

        communityComment.setUI_student_no(jwtToken.getUserPk(token));
        ro.setData(communityCommentService.postCommunityComment(communityComment));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

    @DeleteMapping("/comment")
    public ResponseEntity<FendResponseObject<Integer>> deleteCommunityComments(
            HttpServletRequest req,
            HttpServletResponse res,
            @RequestParam Integer comment_no,
            @RequestParam Integer board_no
    ) {
        String token = jwtToken.resolveToken(req);
        FendResponseObject<Integer> ro = new FendResponseObject<>("Success");
        ro.setMessage("댓글 삭제");

        Integer student_no = jwtToken.getUserPk(token);

        // jwt 유효성 검증
        if (!jwtToken.validateToken(token) || !communityCommentService.checkMyCommunityComment(student_no, comment_no, board_no))
            return new ResponseEntity<>(ro, HttpStatus.FORBIDDEN);

        ro.setData(communityCommentService.deleteCommunityComment(student_no, comment_no, board_no));

        return new ResponseEntity<>(ro, HttpStatus.OK);
    }

}
