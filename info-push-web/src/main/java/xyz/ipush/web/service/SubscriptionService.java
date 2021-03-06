package xyz.ipush.web.service;

import com.github.pagehelper.PageInfo;
import xyz.ipush.web.entity.Subscription;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jwei
 * @since 2021/03/06
 */
public interface SubscriptionService extends IService<Subscription> {

    /**
     * 查询当前用户订阅信息
     *
     * @param page   页码
     * @param size   页大小
     * @param userId 用户id
     * @return 订阅信息分页数据
     */
    PageInfo<Map<String, Object>> list(Integer page, Integer size, String userId);
}
