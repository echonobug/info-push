package xyz.ipush.web.vo;

import lombok.Data;
import lombok.NonNull;

/**
 * 任务 key
 *
 * @author jwei
 * @date 2021/3/8
 */
@Data
public class TaskKeyVO {
    @NonNull
    private String id;
    @NonNull
    private String type;
}
