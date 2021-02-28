package xyz.ipush.web.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import xyz.ipush.web.service.UserService;
import xyz.ipush.web.util.TokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 校验Token
 *
 * @author jwei
 * @date 2020/12/17
 */
@Component
@Slf4j
public class TokenFilter extends OncePerRequestFilter {
    private final TokenUtil tokenUtil;

    private final UserService userService;

    public TokenFilter(TokenUtil tokenUtil, UserService userService) {
        this.tokenUtil = tokenUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = request.getHeader(tokenUtil.getHeader());
        if (StringUtils.hasLength(token) && !tokenUtil.isTokenExpired(token)) {
            String username = tokenUtil.getUsernameFromToken(token);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (username != null && authentication == null) {
                UserDetails userDetails = ((UserDetailsService) userService).loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
