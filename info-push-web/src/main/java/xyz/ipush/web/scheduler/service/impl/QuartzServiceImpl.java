package xyz.ipush.web.scheduler.service.impl;

import org.quartz.*;
import org.springframework.stereotype.Service;
import xyz.ipush.web.scheduler.job.IPushJob;
import xyz.ipush.web.scheduler.service.QuartzService;

import javax.annotation.Resource;

/**
 * @author jwei
 * @date 2021/3/7
 */
@Service
public class QuartzServiceImpl implements QuartzService {
    @Resource
    private Scheduler scheduler;

    @Override
    public void scheduleJob(String jobType, String jobName, String cron) throws SchedulerException {
        JobDetail jobDetail = JobBuilder
                .newJob(IPushJob.class)
                .withIdentity(jobName, jobType)
                .build();
        Trigger build = TriggerBuilder
                .newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();
        scheduler.scheduleJob(jobDetail, build);
    }

    @Override
    public void deleteJob(String jobGroup, String jobName) throws SchedulerException {
        scheduler.deleteJob(new JobKey(jobName, jobGroup));
    }

}
