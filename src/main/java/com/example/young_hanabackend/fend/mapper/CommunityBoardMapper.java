package com.example.young_hanabackend.fend.mapper;

import com.example.young_hanabackend.entity.CommunityBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityBoardMapper {
    List<CommunityBoard> selectCommunityBoardList(int topic, int limit_start, int limit_end);
    CommunityBoard selectCommunityBoard(int board_no);
    int insertCommunityBoard(@Param("CB") CommunityBoard communityBoard,@Param("student_no") int student_no);
}
