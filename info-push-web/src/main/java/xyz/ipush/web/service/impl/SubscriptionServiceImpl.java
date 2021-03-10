package xyz.ipush.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import xyz.ipush.web.entity.Subscription;
import xyz.ipush.web.exception.IPushException;
import xyz.ipush.web.mapper.SubscriptionMapper;
import xyz.ipush.web.scheduler.job.IPushJob;
import xyz.ipush.web.service.SubscriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.ipush.web.service.TaskService;
import xyz.ipush.web.util.SecurityContextUtil;
import xyz.ipush.web.vo.SubscriptionVO;
import xyz.ipush.web.vo.TaskKeyVO;

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

    @Resource
    private TaskService taskService;

    @Override
    public PageInfo<Map<String, Object>> list(Integer page, Integer size, String userId) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = subscriptionMapper.listByUserId(userId);
        return new PageInfo<>(list);
    }

    @Override
    public void subscribe(SubscriptionVO subscriptionVO) throws IPushException {
        Subscription subscription = new Subscription();
        subscription.setInfoDefineId(subscriptionVO.getId());
        subscription.setUserId(SecurityContextUtil.getUser().getId());
        subscription.setCron(subscriptionVO.getCron());
        save(subscription);
        taskService.online(new TaskKeyVO(subscription.getId(), IPushJob.SEND));
    }

    @Override
    public void cancel(String id) throws IPushException {
        TaskKeyVO taskKeyVO = new TaskKeyVO(id, IPushJob.SEND);
        taskService.offline(taskKeyVO);
        taskService.clearTaskLog(taskKeyVO);
        removeById(id);
    }
}
