package com.example.young_hanabackend.security.logic;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Log4j2
@Component
public class CustomJwtFilter extends GenericFilterBean {

    private JwtToken jwtToken;

    @Autowired
    public CustomJwtFilter(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String token = jwtToken.resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();

        if (jwtToken.validateToken(token)) {
            // 인증 완료. SecurityContextHolder에 등록해야 인증된 사용자라고 생각한다.
            AbstractAuthenticationToken authentication = jwtToken.getAuthentication(token, httpServletRequest);
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);  //인증 정보 넣기
            SecurityContextHolder.setContext(securityContext);  // 다시 등록
            log.info("Security Context에 '{}' 및 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), authentication.getAuthorities(), requestURI);
        } else {
            log.info("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
