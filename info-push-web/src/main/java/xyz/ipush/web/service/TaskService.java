package xyz.ipush.web.service;

import com.github.pagehelper.PageInfo;
import xyz.ipush.web.exception.IPushException;
import xyz.ipush.web.vo.TaskKeyVO;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jwei
 * @date 2020/12/16
 */
public interface TaskService {
    /**
     * 分页查询任务
     *
     * @param page 页码
     * @param size 页大小
     * @return 页面信息
     */
    PageInfo<Map<String, Object>> list(Integer page, Integer size);

    /**
     * 上线任务
     *
     * @param taskKeyVO 任务key
     * @throws IPushException 如果上线失败
     */
    void online(TaskKeyVO taskKeyVO) throws IPushException;

    /**
     * 下线任务
     *
     * @param taskKeyVO 任务key
     * @throws IPushException 如果下线失败
     */
    void offline(TaskKeyVO taskKeyVO) throws IPushException;

    /**
     * 清理任务日志
     *
     * @param taskKeyVO 任务key
     */
    void clearTaskLog(TaskKeyVO taskKeyVO);
}
