package xyz.ipush.web.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ipush.web.entity.Job;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jwei
 * @since 2021/03/07
 */
public interface JobService extends IService<Job> {
    /**
     * 分页查询任务执行记录
     *
     * @param groupName 分组名
     * @param jobName   任务名
     * @param page      页码
     * @param size      页大小
     * @return 分页数据
     */
    Page<Job> log(String groupName, String jobName, Integer page, Integer size);
}
