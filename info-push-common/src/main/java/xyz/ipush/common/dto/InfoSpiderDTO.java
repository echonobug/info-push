package xyz.ipush.common.dto;

import lombok.Data;
import xyz.ipush.common.pojo.IPushFind;

import java.util.List;

/**
 * info_define
 *
 * @author jwei
 * @date 2021/3/2
 */
@Data
public class InfoSpiderDTO {
    private String id;
    private List<IPushFind> find;
}
