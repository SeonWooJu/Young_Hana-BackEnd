package com.example.young_hanabackend.fend.service;

import com.example.young_hanabackend.entity.CommunityComment;
import com.example.young_hanabackend.fend.mapper.CommunityCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityCommentService {

    CommunityCommentMapper communityCommentMapper;

    @Autowired
    public CommunityCommentService(CommunityCommentMapper communityCommentMapper) {
        this.communityCommentMapper = communityCommentMapper;
    }

    public List<CommunityComment> getCommunityCommentList(Integer board_no) {
        return communityCommentMapper.selectCommunityCommentList(board_no);
    }

    public Integer postCommunityComment(CommunityComment communityComment) {
        int num = communityCommentMapper.insertCommunityComment(communityComment);
        if (num != 0)
            return null;

        return num;
    }

    public Integer deleteCommunityComment(int student_no, int comment_no, int board_no) {
        int num = communityCommentMapper.deleteCommunityComment(student_no, comment_no, board_no);
        if (num != 0)
            return null;

        return num;
    }

    /** 게시물을 수정, 삭제 하기전 권한을 체크하는 함수 **/
    public boolean checkMyCommunityComment(int student_no, int comment_no, int board_no) {
        return communityCommentMapper.selectMyCommunityComment(student_no, comment_no, board_no) == 1;
    }
}
