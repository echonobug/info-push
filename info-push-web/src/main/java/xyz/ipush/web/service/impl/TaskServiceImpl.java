package xyz.ipush.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;
import xyz.ipush.web.entity.Job;
import xyz.ipush.web.exception.ErrorCodeEnum;
import xyz.ipush.web.exception.IPushException;
import xyz.ipush.web.mapper.TaskMapper;
import xyz.ipush.web.scheduler.job.IPushJob;
import xyz.ipush.web.scheduler.service.QuartzService;
import xyz.ipush.web.service.InfoDefineService;
import xyz.ipush.web.service.JobService;
import xyz.ipush.web.service.SubscriptionService;
import xyz.ipush.web.service.TaskService;
import xyz.ipush.web.vo.TaskKeyVO;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jwei
 * @date 2021/3/7
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private QuartzService quartzService;
    @Resource
    private InfoDefineService infoDefineService;
    @Resource
    private SubscriptionService subscriptionService;
    @Resource
    private JobService jobService;

    @Override
    public PageInfo<Map<String, Object>> list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = taskMapper.listTask();
        return new PageInfo<>(list);
    }

    @Override
    public void online(TaskKeyVO taskKeyVO) throws IPushException {
        String cron = switch (taskKeyVO.getType()) {
            case IPushJob.FIND -> infoDefineService.getById(taskKeyVO.getId()).getUpdateCron();
            case IPushJob.SEND -> subscriptionService.getById(taskKeyVO.getId()).getCron();
            default -> throw IPushException.build(ErrorCodeEnum.UNSUPPORTED_TASK_TYPE);
        };
        try {
            quartzService.scheduleJob(taskKeyVO.getType(), taskKeyVO.getId(), cron);
        } catch (SchedulerException e) {
            log.error(e.getMessage());
            throw IPushException.build(ErrorCodeEnum.TASK_ONLINE_ERROR);
        }
    }

    @Override
    public void offline(TaskKeyVO taskKeyVO) throws IPushException {
        try {
            quartzService.deleteJob(taskKeyVO.getType(), taskKeyVO.getId());
        } catch (SchedulerException e) {
            log.error(e.getMessage());
            throw IPushException.build(ErrorCodeEnum.TASK_OFFLINE_ERROR);
        }
    }

    @Override
    public void clearTaskLog(TaskKeyVO taskKeyVO) {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<Job>().eq("group_name", taskKeyVO.getType()).eq("job_name", taskKeyVO.getId());
        jobService.remove(queryWrapper);
    }

}
