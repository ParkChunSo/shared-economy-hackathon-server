package com.hackathon.sharedeconomy.utill;

import com.hackathon.sharedeconomy.model.enums.RoleType;
import io.jsonwebtoken.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

//@Component
public class JwtProvider {
    private static final long validityInMilliseconds = 360000000; // 테스트 중이라 100시간으로 설정.
    private static String secretKey = Base64.getEncoder().encodeToString("PCS".getBytes());


//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }

    public static String createToken(String username, RoleType role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 시간 체크
    public static boolean validateToken(String token) throws JwtException, IllegalArgumentException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return claims.getBody().getExpiration().after(new Date());
    }

    // bearer 토큰 불러오기
    public static String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static String resolveToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // 사용자 아이디
    public static String getUserIdByToken(String bearerToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(resolveToken(bearerToken)).getBody().getSubject();
    }

    // 사용자 권한
    public static RoleType getUserRoleByToken(String bearerToken) {
        String role = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(resolveToken(bearerToken)).getBody().get("role", String.class);

        if (String.valueOf(role).matches("(?i)ADMIN"))
            return RoleType.ADMIN;
        else if (String.valueOf(role).matches("(?i)OLD"))
            return RoleType.OLD;
        else if (String.valueOf(role).matches("(?i)YOUNG"))
            return RoleType.YOUNG;

        return null;
    }
}