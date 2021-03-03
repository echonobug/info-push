package xyz.ipush.common.pojo;

import lombok.Data;

/**
 * 信息推送规则
 *
 * @author jwei
 * @date 2021/3/3
 */
@Data
public class IPushSend {
    /**
     * 信息类型
     */
    private String type;
    /**
     * 信息内容
     */
    private String content;
}
