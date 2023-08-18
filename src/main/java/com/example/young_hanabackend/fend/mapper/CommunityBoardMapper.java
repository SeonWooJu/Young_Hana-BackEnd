package com.example.young_hanabackend.fend.mapper;

import com.example.young_hanabackend.entity.CommunityBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityBoardMapper {
    List<CommunityBoard> selectCommunityBoardList(int topic, int limit_start, int limit_end);
    CommunityBoard selectCommunityBoard(int board_no);
    int insertCommunityBoard(@Param("CB") CommunityBoard communityBoard);
    int updateCommunityBoard(@Param("CB") CommunityBoard communityBoard);

    /** 게시물을 수정, 삭제 하기전 권한을 체크하는 함수 **/
    int selectMyCommunityBoard(int student_no, int board_no);
}
