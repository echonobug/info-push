package xyz.ipush.web.service;

import com.github.pagehelper.PageInfo;
import xyz.ipush.web.entity.Subscription;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ipush.web.exception.IPushException;
import xyz.ipush.web.vo.SubscriptionVO;

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

    /**
     * 信息订阅
     *
     * @param subscriptionVO 信息标识
     * @throws IPushException 如果订阅失败
     */
    void subscribe(SubscriptionVO subscriptionVO) throws IPushException;

    /**
     * 取消订阅
     *
     * @param id 订阅id
     * @throws IPushException 如果取消失败
     */
    void cancel(String id) throws IPushException;
}
