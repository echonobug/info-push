package fun.jwei.ipush.web.config.log.access;

import fun.jwei.ipush.web.entity.AccessLog;
import fun.jwei.ipush.web.entity.User;
import fun.jwei.ipush.web.mapper.AccessLogMapper;
import fun.jwei.ipush.web.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class AccessLogInterceptor implements HandlerInterceptor {

    @Autowired
    private AccessLogMapper accessLogMapper;

    /**
     * 请求开始时间标识
     */
    private static final String LOGGER_SEND_TIME = "SEND_TIME";
    /**
     * 请求日志实体标识
     */
    private static final String LOGGER_ACCESS_LOG = "ACCESS_LOG_ENTITY";

    /**
     * 未登录用户
     */
    private static final String GUEST_USER_NAME = "未登录用户";

    /**
     * 进入Controller之前开始记录日志实体
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
        AccessLog accessLog = new AccessLog();
        accessLog.setRequestIp(IpUtil.getIpAdrress(request));
        accessLog.setRequestMethod(request.getMethod());
        accessLog.setRequestUrl(request.getRequestURI());
        request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
        request.setAttribute(LOGGER_ACCESS_LOG, accessLog);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        AccessLog accessLog = (AccessLog) request.getAttribute(LOGGER_ACCESS_LOG);
        int status = response.getStatus();
        accessLog.setResponseStatus(status);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            accessLog.setUserName(((User) principal).getUsername());
        } else {
            accessLog.setUserName(GUEST_USER_NAME);
        }
        long currentTime = System.currentTimeMillis();
        long snedTime = Long.parseLong(request.getAttribute(LOGGER_SEND_TIME).toString());
        accessLog.setRequestDuration(currentTime - snedTime);
        accessLogMapper.insertSelective(accessLog);
        log.info(accessLog.toString());
    }
}

