package com.example.young_hanabackend.entity;

import lombok.Data;

import java.util.List;

@Data
public class CommunityBoardList {
    List<CommunityBoard> communityBoardList;
    int limit_start;
    int limit_end;
}
