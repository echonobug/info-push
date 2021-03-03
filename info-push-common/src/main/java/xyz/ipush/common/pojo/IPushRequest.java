package xyz.ipush.common.pojo;

import lombok.Data;

/**
 * 请求规则
 *
 * @author jwei
 * @date 2021/3/3
 */
@Data
public class IPushRequest {
    private String url;
    private String method;
    private String header;
    private String cookies;
}
