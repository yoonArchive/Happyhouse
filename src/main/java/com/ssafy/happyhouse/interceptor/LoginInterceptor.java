package com.ssafy.happyhouse.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final String SALT = "happyHouseUser";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) {
        log.debug("preHandle() 메소드 실행");
        Enumeration<String> headerNames = request.getHeaderNames();

        final String token = request.getHeader("access-token");
        log.debug("토큰 정보 : {}",token);
        //토큰이 유효한 경우 정상적으로 요청 넘김
        try {
            Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}