package ipush.mapper;

import ipush.entity.AccessLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author jwei
 * @date 2020/12/18
 */
@Mapper
public interface AccessLogMapper extends BaseMapper<AccessLog> {
    /**
     * 插入一条记录
     *
     * @param record 记录
     * @return 受影响的记录数
     */
    int insertSelective(AccessLog record);
}
