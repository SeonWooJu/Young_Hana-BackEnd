package com.example.young_hanabackend.fend.service;

import com.example.young_hanabackend.entity.CommunityBoard;
import com.example.young_hanabackend.fend.mapper.CommunityBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityBoardService {

    CommunityBoardMapper communityBoardMapper;

    @Autowired
    public CommunityBoardService(CommunityBoardMapper communityBoardMapper) {
        this.communityBoardMapper = communityBoardMapper;
    }

    public List<CommunityBoard> getCommunityBoardList(int topic, int limit_start, int limit_end) {
        return communityBoardMapper.selectCommunityBoardList(topic, limit_start, limit_end);
    }

    public CommunityBoard getCommunityBoard(int board_no) {
        return communityBoardMapper.selectCommunityBoard(board_no);
    }

    public Integer postCommunityBoard(CommunityBoard communityBoard) {
        Integer num = communityBoardMapper.insertCommunityBoard(communityBoard);
        if (num != 1)
            return null;

        return num;
    }

    public Integer putCommunityBoard(CommunityBoard communityBoard) {
        Integer num = communityBoardMapper.updateCommunityBoard(communityBoard);
        if (num != 1)
            return null;

        return num;
    }

    public Integer deleteCommunityBoard(int board_no) {
        Integer num = communityBoardMapper.deleteCommunityBoard(board_no);
        if (num != 1)
            return null;

        return num;
    }

    /** 게시물을 수정, 삭제 하기전 권한을 체크하는 함수 **/
    public boolean checkMyCommunityBoard(int student_no, int board_no) {
        return communityBoardMapper.selectMyCommunityBoard(student_no, board_no) == 1;
    }
}
