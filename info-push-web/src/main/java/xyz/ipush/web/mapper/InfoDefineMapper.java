package xyz.ipush.web.mapper;

import xyz.ipush.web.entity.InfoDefine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author jwei
 * @date 2021/03/16
 */
@Mapper
public interface InfoDefineMapper extends BaseMapper<InfoDefine> {
    /**
     * 插入一条记录
     *
     * @param record 记录
     * @return 受影响的记录数
     */
    int insertSelective(InfoDefine record);

    /**
     * 根据条件查询信息定义
     *
     * @param define 条件
     * @return 结果列表
     */
    List<InfoDefine> list(InfoDefine define);
}
