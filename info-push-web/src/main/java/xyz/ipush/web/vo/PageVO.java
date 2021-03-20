package xyz.ipush.web.vo;

import lombok.Data;

/**
 * 翻页信息
 *
 * @author jwei
 * @date 2021/3/10
 */
@Data
public class PageVO {
    private Integer page;
    private Integer size;
}
