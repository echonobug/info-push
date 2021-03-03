package xyz.ipush.common.pojo;

import lombok.Data;

import java.util.List;

/**
 * info_define content
 *
 * @author jwei
 * @date 2021/3/3
 */
@Data
public class IPushInfoDefineContent {
    private List<IPushFind> find;
    private IPushSend send;
}
