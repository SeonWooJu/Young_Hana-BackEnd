package com.example.young_hanabackend.security.mapper;

import com.example.young_hanabackend.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    // 계정 개수 가져오기
    int selectAccountCount(int student_no);

    // 계정 추가
    int insertAccount(@Param("user") UserInfo user);

    // 계정 아이디 비밀번호 체크
    UserInfo selectAccountStudentNoAndPw(int student_no);
}
