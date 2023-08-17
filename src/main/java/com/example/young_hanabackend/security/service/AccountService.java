package com.example.young_hanabackend.security.service;

import com.example.young_hanabackend.entity.UserInfo;
import com.example.young_hanabackend.security.logic.AES256;
import com.example.young_hanabackend.security.logic.JwtToken;
import com.example.young_hanabackend.security.mapper.AccountMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

@Service
public class AccountService {

    AccountMapper accountMapper;
    JwtToken jwtToken;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountMapper accountMapper, JwtToken jwtToken) {
        this.accountMapper = accountMapper;
        this.jwtToken = jwtToken;
    }

    public Boolean checkStudentNo (UserInfo user) {
        if (user.getUI_birth() == null || user.getUI_name() == null || user.getUI_group() == null || user.getUI_student_no() == null)
            return false;

        try {
            // 강원대 api 사용
            URL url = new URL(
                    "https://kcloud.kangwon.ac.kr/data/ssm01.ssm01002/dlt_01_s1" +
                            "?GROUP_NAME=" + user.getUI_group() +
                            "&NAME=" + user.getUI_name() +
                            "&BIRTH=" + new SimpleDateFormat("yyMMdd").format(user.getUI_birth()) +
                            "&JDBC=CommAction"
            );

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // utf-8 로 보내기 위해 사용.
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            // 실질적 get 요청을 보내는 부분
            conn.connect();

            // 응답 받은 내용을 BufferedReader를 사용하여 차곡차곡 저장
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();

            String responseData = "";
            while ((responseData = br.readLine()) != null) {
                sb.append(responseData);
            }

            // json Data를 파싱한다.
            JSONObject jsonObj = new JSONObject(sb.toString());
            JSONArray jsonArr = jsonObj.getJSONArray("data");

            // 파싱한 Data를 사용하여 현재 사용자 정보가 공식 정보와 일치하는지 확인 한다.
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject data = jsonArr.getJSONObject(i);

                if (Integer.parseInt(data.getString("USER_ID")) == user.getUI_student_no())
                    return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Integer singUp (UserInfo user) {
        // 학번의 유효성 검사 || 중복 회원가입 방지
        if (!checkStudentNo(user) || accountMapper.selectAccountCount(user.getUI_student_no()) != 0)
            return null;

        // spring boot security를 이용한 비밀번호 암호화
        String encodedpwd = passwordEncoder.encode(user.getUI_pw());
        user.setUI_pw(encodedpwd);

        // 회원가입이 잘 되었는지 확인
        if (accountMapper.insertAccount(user) != 1)
            return null;

        return user.getUI_student_no();
    }

    public String singIn (UserInfo user) {
        try {
            String pw = accountMapper.selectAccountStudentNoAndPw(user.getUI_student_no());

            // 비밀번호가 일치하지 않다면 null return
            if (!passwordEncoder.matches(user.getUI_pw(), pw))
                return null;

            return jwtToken.createToken(user.getUI_student_no());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
