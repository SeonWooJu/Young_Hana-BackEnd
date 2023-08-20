package com.example.young_hanabackend.security.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    String selectUserRole(int student_no);
}
