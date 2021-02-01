package xyz.ipush.web.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT Token 工具类
 *
 * @author jwei
 * @date 2020/12/16
 */
@Data
@Component
@ConfigurationProperties("ipush.token")
public class TokenUtil {

    /**
     * jwt签名密钥
     */
    private String secret;

    /**
     * token 过期时间（毫秒）
     */
    private Long expiration;

    /**
     * token rememberMe过期时间（毫秒）
     */
    private Long rememberMeExpiration;

    /**
     * token header名称
     */
    private String header;

    /**
     * 刷新token的时机，token使用时长/token有效期 大于该值才会刷新
     */
    private Double refreshPoint;

    /**
     * 生成token令牌
     *
     * @param userDetails 用户
     * @return 令token牌
     */
    public String generateToken(UserDetails userDetails, Boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>(8);
        claims.put(Claims.AUDIENCE, userDetails.getUsername());
        Date now = new Date();
        claims.put(Claims.ISSUED_AT, now);
        Long exp = rememberMe ? rememberMeExpiration : expiration;
        claims.put(Claims.EXPIRATION, new Date(now.getTime() + exp));
        return generateToken(claims);
    }


    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getAudience();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        Date now = new Date();
        if (expiration.before(now)) {
            return null;
        }
        Date issuedAt = claims.getIssuedAt();
        long exp = expiration.getTime() - issuedAt.getTime();
        double point = (now.getTime() - issuedAt.getTime()) * 1.0 / exp;
        if (point < refreshPoint) {
            return token;
        }
        claims.setIssuedAt(now);
        claims.setExpiration(new Date(now.getTime() + exp));
        return generateToken(claims);
    }

    /**
     * 生成token
     *
     * @param claims 声明
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

}
