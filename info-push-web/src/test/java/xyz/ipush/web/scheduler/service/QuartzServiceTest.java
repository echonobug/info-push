package xyz.ipush.web.scheduler.service;

import org.junit.jupiter.api.Test;
import org.quartz.SchedulerException;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.ipush.web.scheduler.job.IPushJob;

import javax.annotation.Resource;

/**
 * quartz service test
 *
 * @author jwei
 * @date 2021/3/7
 */
@SpringBootTest
public class QuartzServiceTest {
    @Resource
    private QuartzService quartzService;

    @Test
    public void scheduleJob() throws SchedulerException, InterruptedException {
        quartzService.deleteJob(IPushJob.SEND,"1368188882050007041");
        quartzService.scheduleJob(IPushJob.SEND,"1368188882050007041","30 11 22 * * ? *");
        Thread.sleep(60000);
        quartzService.deleteJob(IPushJob.SEND,"1368188882050007041");
    }
}
