package xyz.ipush.web.dto;

import lombok.Data;

/**
 * 信息中心DTO
 *
 * @author jwei
 * @date 2021/3/9
 */
@Data
public class InfoDTO {
    private String id;
    private String title;
    private String description;
    private String image;
    private Boolean concerned;
    private String subscriptionId;
}
