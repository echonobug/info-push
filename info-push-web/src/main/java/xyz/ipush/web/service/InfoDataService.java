package xyz.ipush.web.service;

import xyz.ipush.web.entity.InfoData;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jwei
 * @since 2021/03/02
 */
public interface InfoDataService extends IService<InfoData> {
    /**
     * 处理 kafka 消息
     *
     * @param content
     */
    void processMessage(String content);
}
