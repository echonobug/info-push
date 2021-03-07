package xyz.ipush.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import xyz.ipush.web.mapper.TaskMapper;
import xyz.ipush.web.service.TaskService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author jwei
 * @date 2021/3/7
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private TaskMapper taskMapper;

    @Override
    public PageInfo<Map<String, Object>> list(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = taskMapper.listTask();
        return new PageInfo<>(list);
    }

}
