package xyz.ipush.web.mapper;

import xyz.ipush.web.entity.Job;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author jwei
 * @date 2021/03/07
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {
    /**
     * 插入一条记录
     *
     * @param record 记录
     * @return 受影响的记录数
     */
    int insertSelective(Job record);
}
