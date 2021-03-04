package xyz.ipush.web.mapper;

import xyz.ipush.web.entity.InfoData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author jwei
 * @date 2021/03/02
 */
@Mapper
public interface InfoDataMapper extends BaseMapper<InfoData> {
    /**
     * 插入一条记录
     *
     * @param record 记录
     * @return 受影响的记录数
     */
    int insertSelective(InfoData record);
}
