package com.example.young_hanabackend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunityBoard {
    private String writer;
    private Integer student_no;
    private Integer CB_no;
    private String CB_title;
    private String CB_content;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp CB_cr_date;
    private Integer CB_topic;
    private Boolean CB_delete_T_F;
    private Boolean my_board;
}