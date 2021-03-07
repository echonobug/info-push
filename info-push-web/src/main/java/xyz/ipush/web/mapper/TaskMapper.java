package xyz.ipush.web.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author jwei
 * @date 2021/03/07
 */
@Mapper
public interface TaskMapper {

    /**
     * 获取任务列表
     *
     * @return 任务列表
     */
    List<Map<String, Object>> listTask();

}
