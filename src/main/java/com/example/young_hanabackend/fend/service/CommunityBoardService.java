package com.example.young_hanabackend.fend.service;

import com.example.young_hanabackend.entity.CommunityBoard;
import com.example.young_hanabackend.entity.CommunityBoardList;
import com.example.young_hanabackend.fend.mapper.CommunityBoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityBoardService {

    CommunityBoardMapper communityBoardMapper;

    @Autowired
    public CommunityBoardService(CommunityBoardMapper communityBoardMapper) {
        this.communityBoardMapper = communityBoardMapper;
    }

    public CommunityBoardList getCommunityBoardList(int topic, int limit_start, int limit_end) {
        CommunityBoardList list = new CommunityBoardList();

        list.setLimit_start(limit_start);
        list.setLimit_end(limit_end);
        list.setCommunityBoardList(communityBoardMapper.selectCommunityBoardList(topic, limit_start, limit_end));

        return list;
    }

    public CommunityBoard getCommunityBoard(int board_no) {
        return communityBoardMapper.selectCommunityBoard(board_no);
    }

    public Integer postCommunityBoard(CommunityBoard communityBoard) {
        Integer idx = communityBoardMapper.insertCommunityBoard(communityBoard);
        if (idx == 0)
            return null;

        return idx;
    }
}
