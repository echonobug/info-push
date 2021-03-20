package xyz.ipush.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * 页面解析规则
 *
 * @author jwei
 * @date 2021/3/3
 */
@Data
public class IPushParse {
    public static final String XPATH = "XPath";
    public static final String JSONPATH = "JsonPath";
    /**
     * 解析器
     */
    private String parser;

    /**
     * 待解析内容
     */
    private List<IPushField> content;

}
