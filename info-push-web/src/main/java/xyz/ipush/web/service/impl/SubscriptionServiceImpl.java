package xyz.ipush.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import xyz.ipush.web.entity.Subscription;
import xyz.ipush.web.mapper.SubscriptionMapper;
import xyz.ipush.web.service.SubscriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订阅表  服务实现类
 * </p>
 *
 * @author jwei
 * @since 2021/03/06
 */
@Service
public class SubscriptionServiceImpl extends ServiceImpl<SubscriptionMapper, Subscription> implements SubscriptionService {

    @Resource
    private SubscriptionMapper subscriptionMapper;

    @Override
    public PageInfo<Map<String, Object>> list(Integer page, Integer size, String userId) {
        PageHelper.startPage(page, size);
        List<Map<String,Object>> list = subscriptionMapper.listByUserId(userId);
        return new PageInfo<>(list);
    }
}
