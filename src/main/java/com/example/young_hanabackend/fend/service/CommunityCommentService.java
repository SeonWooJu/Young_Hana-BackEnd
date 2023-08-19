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

    public List<CommunityComment> getCommunityCommentsList(Integer board_no) {
        return communityCommentMapper.selectCommunityCommentList(board_no);
    }

    public Integer postCommunityComments(CommunityComment communityComment) {
        int num = communityCommentMapper.insertCommunityComment(communityComment);
        if (num != 0)
            return null;

        return num;
    }
}
