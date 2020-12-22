package fun.jwei.ipush.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.jwei.ipush.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author jwei
 * @date 2020/12/22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 插入一条记录
     *
     * @param record 记录
     * @return 受影响的记录数
     */
    int insertSelective(User record);
}
