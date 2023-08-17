package com.example.young_hanabackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo {

    private Integer UI_student_no;

    private String UI_name;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date UI_birth;

    private String UI_email;

    private String UI_phone_no;

    private String UI_pw;

    private String UI_group;

    private Integer UI_role;

    private String UI_github_url;

}
