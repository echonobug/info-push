package xyz.ipush.web.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 环境属性工具类
 *
 * @author jwei
 * @date 2021/2/23
 */
@Data
@Component
@ConfigurationProperties("ipush.server")
public class EnvUtil {
    /**
     * 服务器协议
     */
    private String protocol;

    /**
     * 服务器 host
     */
    private String host;

    /**
     * 服务器监听端口
     */
    private String port;

    public String getServerUrl(){
        return String.format("%s://%s:%s", protocol,host,port);
    }

}
