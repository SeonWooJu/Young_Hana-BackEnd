package com.example.young_hanabackend.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Data
public class CommunityComments {
    private Integer CC_no;
    private Integer UI_student_no;
    private Integer CB_no;
    private String CC_content;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp CC_cr_date;
}
