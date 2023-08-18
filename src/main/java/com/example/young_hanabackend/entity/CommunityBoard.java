package com.example.young_hanabackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunityBoard {
    int CB_no;
    String CB_title;
    String CB_content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date CB_or_date;
    int CB_kind;
    boolean CB_delete_T_F;
}
