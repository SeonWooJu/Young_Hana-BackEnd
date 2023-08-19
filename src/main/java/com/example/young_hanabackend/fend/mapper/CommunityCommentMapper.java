package com.example.young_hanabackend.fend.mapper;

import com.example.young_hanabackend.entity.CommunityComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityCommentMapper {
    List<CommunityComment> selectCommunityCommentList(Integer board);

    int insertCommunityComment(@Param("CC") CommunityComment communityComment);
}
