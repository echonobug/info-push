package xyz.ipush.common.pojo;

import lombok.Data;

import java.util.Map;

/**
 * 待解析字段
 *
 * @author jwei
 * @date 2021/3/3
 */
@Data
public class IPushField {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 内容选择器
     */
    private String selector;
    /**
     * 额外属性
     */
    private Map<String, Object> extra;
}
