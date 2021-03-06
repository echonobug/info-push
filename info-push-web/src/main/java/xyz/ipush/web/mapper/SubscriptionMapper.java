package xyz.ipush.web.mapper;

import xyz.ipush.web.entity.Subscription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author jwei
 * @date 2021/03/06
 */
@Mapper
public interface SubscriptionMapper extends BaseMapper<Subscription> {
    /**
     * 插入一条记录
     *
     * @param record 记录
     * @return 受影响的记录数
     */
    int insertSelective(Subscription record);

    /**
     * 获取当前用户订阅信息
     *
     * @param userId 用户id
     * @return 当前用户订阅信息
     */
    List<Map<String,Object>> listByUserId(String userId);
}
