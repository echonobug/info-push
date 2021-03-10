package xyz.ipush.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import xyz.ipush.web.entity.Job;
import xyz.ipush.web.mapper.JobMapper;
import xyz.ipush.web.service.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 任务执行记录  服务实现类
 * </p>
 *
 * @author jwei
 * @since 2021/03/07
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Override
    public Page<Job> log(String groupName, String jobName, Integer page, Integer size) {
        return query().eq("group_name", groupName).eq("job_name", jobName).page(new Page<>(page, size));
    }
}
