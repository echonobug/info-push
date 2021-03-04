package xyz.ipush.common.dto;

import lombok.Data;

import java.util.Map;

/**
 * info_data
 *
 * @author jwei
 * @date 2021/3/4
 */
@Data
public class InfoDataDTO {
    private String defineId;
    private Map<String,Object> content;
}
