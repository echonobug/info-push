package xyz.ipush.web.service;

import com.github.pagehelper.PageInfo;

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
}
