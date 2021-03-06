package xyz.ipush.web.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

/**
 * subscription
 *
 * @author jwei
 * @date 2021/3/6
 */
@Data
public class SubscriptionVO {
    @ApiModelProperty("info_define中id")
    @NonNull
    private String id;
    @ApiModelProperty("subscription中cron")
    @NonNull
    private String cron;
}
