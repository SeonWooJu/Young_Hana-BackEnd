package com.example.young_hanabackend.fend.mapper;

import com.example.young_hanabackend.entity.CommunityBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityBoardMapper {
    List<CommunityBoard> selectCommunityBoardList(int topic, int limit_start, int limit_end);
}
