package xyz.ipush.web.scheduler.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import xyz.ipush.web.service.InfoDefineService;

import javax.annotation.Resource;

/**
 * 任务
 *
 * @author jwei
 * @date 2021/3/7
 */
@Slf4j
@Component
public class IPushJob extends QuartzJobBean {
    public static final String FIND = "find";
    public static final String SEND = "send";

    @Resource
    private InfoDefineService infoDefineService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobKey key = context.getJobDetail().getKey();
        switch (key.getGroup()) {
            case FIND:
                infoDefineService.find(key.getName());
                break;
            case SEND:
                infoDefineService.send(key.getName());
                break;
            default:
                log.warn("不支持的任务类型:{}", key.getGroup());
        }
    }
}
