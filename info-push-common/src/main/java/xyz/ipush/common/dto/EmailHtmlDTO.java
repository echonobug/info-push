package xyz.ipush.common.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * email html
 *
 * @author jwei
 * @date 2021/3/5
 */
@Data
public class EmailHtmlDTO {
    private String subject;
    private List<String> to;
    private Map<String, Object> model;
    private String html;
}
