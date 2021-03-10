package xyz.ipush.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.ipush.web.dto.InfoDTO;

import java.util.List;

/**
 * @author jwei
 */
@Mapper
public interface InfoMapper {
    /**
     * 查询所有信息定义（附带用户关注信息）
     *
     * @param keyword 搜索关键字
     * @param userId  用户id
     * @return 信息定义数据
     */
    List<InfoDTO> listWithIfConcerned(String keyword, String userId);
}
