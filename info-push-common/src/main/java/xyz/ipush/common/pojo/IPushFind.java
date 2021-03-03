package xyz.ipush.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * 信息爬取规则
 *
 * @author jwei
 * @date 2021/3/3
 */
@Data
public class IPushFind {
    /**
     * 是否需要js渲染
     */
    private Boolean render;
    /**
     * 请求信息
     */
    private IPushRequest request;

    /**
     * 待解析规则
     */
    private List<IPushParse> parse;
}
