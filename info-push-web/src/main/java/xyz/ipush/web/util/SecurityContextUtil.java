package xyz.ipush.web.util;

import xyz.ipush.web.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 访问 Spring Security 上下文 工具类
 *
 * @author jwei
 * @date 2020/12/25
 */
public class SecurityContextUtil {
    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
