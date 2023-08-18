package com.example.young_hanabackend.security.logic;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtToken {
    @Value("${jwt.secret.key}")
    private String secretKey;

    /** 토큰 유효시간 7일 **/
    private long tokenValidTime = 7 * 24 * 60 * 60 * 1000L;

    /** 객체 초기화, secretKey를 Base65로 인코딩한다. **/
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /** JWT 토큰 생성 **/
    public String createToken(int userPk) {
        Claims claims = Jwts.claims().setSubject(new AES256().encrypt(String.valueOf(userPk))); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS512, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    /** 토큰에서 학번 추출 **/
    public Integer getUserPk(String token) {
        return Integer.parseInt(new AES256().decrypt(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject()));
    }

    /** Spring Boot Security에 jwt 등록(URI 허기) **/
    public AbstractAuthenticationToken getAuthentication(String token, HttpServletRequest httpServletRequest) {
        AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(getUserPk(token), null, AuthorityUtils.NO_AUTHORITIES);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        return authentication;
    }

    /** Request의 Header에서 token 값을 가져옵니다. "Authorization" : "Bearer (TOKEN값)' **/
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /** 토큰의 유효성 + 만료일자 확인 **/
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}