package xyz.ipush.web.scheduler.service;

import org.quartz.SchedulerException;

/**
 * 任务调度接口
 *
 * @author jwei
 * @date 2021/3/7
 */
public interface QuartzService {


    /**
     * 上线一个任务
     *
     * @param jobType 任务类型，同时用作任务分组名
     * @param jobName 任务名
     * @param cron    cron 表达式
     * @throws SchedulerException 如果上线失败
     */
    void scheduleJob(String jobType, String jobName, String cron) throws SchedulerException;

    /**
     * 下线一个任务
     *
     * @param jobGroup 分组名
     * @param jobName  任务名
     * @throws SchedulerException 如果下线失败
     */
    void deleteJob(String jobGroup, String jobName) throws SchedulerException;
}
