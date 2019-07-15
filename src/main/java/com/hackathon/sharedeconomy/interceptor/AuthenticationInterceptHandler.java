package com.hackathon.sharedeconomy.interceptor;

import com.hackathon.sharedeconomy.exception.AuthenticationException;
import com.hackathon.sharedeconomy.model.enums.RoleType;
import com.hackathon.sharedeconomy.utill.JwtUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthenticationInterceptHandler extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("================== 토큰 검사 실행 ==================");
        try {
            String token = JwtUtils.resolveToken(request);
            if (token != null && JwtUtils.validateToken(token)) {
                RoleType roles = JwtUtils.getUserRoleByToken(token);
                if(!roles.equals(RoleType.ADMIN))
                    throw new AuthenticationException("해당 요청은 관리자만 가능합니다.");
            }
        } catch (JwtException | IllegalArgumentException e){
            log.error("Expired or invalid JWT token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Expired or invalid JWT token");
            return false;
        }
        return true;
    }
}
