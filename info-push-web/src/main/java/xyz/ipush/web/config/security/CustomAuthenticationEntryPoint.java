package xyz.ipush.web.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import xyz.ipush.web.entity.ResponseEntity;
import xyz.ipush.web.exception.ErrorCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义
 *
 * @author jwei
 * @date 2020/12/21
 */
@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        ResponseEntity error;
        if (authException instanceof UsernameNotFoundException) {
            error = ResponseEntity.error(ErrorCodeEnum.USERNAME_NOT_FOUND);
        } else if (authException instanceof BadCredentialsException) {
            error = ResponseEntity.error(ErrorCodeEnum.PASSWORD_NOT_MATCHED);
        } else if (authException instanceof InsufficientAuthenticationException) {
            error = ResponseEntity.error(ErrorCodeEnum.NOT_LOGIN);
        } else {
            error = ResponseEntity.error(ErrorCodeEnum.UNKNOWN_ERROR);
            log.error(authException.getMessage());
        }
        writer.write(objectMapper.writeValueAsString(error));
    }
}
