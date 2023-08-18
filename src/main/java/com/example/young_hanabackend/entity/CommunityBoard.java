package com.example.young_hanabackend.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunityBoard {
    String writer;
    Integer student_no;
    Integer CB_no;
    String CB_title;
    String CB_content;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Timestamp CB_cr_date;
    Integer CB_topic;
    Boolean CB_delete_T_F;
}