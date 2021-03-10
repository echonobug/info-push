package xyz.ipush.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import xyz.ipush.web.dto.InfoDTO;
import xyz.ipush.web.entity.InfoDefine;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jwei
 * @since 2021/03/02
 */
public interface InfoDefineService extends IService<InfoDefine> {

    /**
     * 发起信息获取消息
     *
     * @param id info_define id
     */
    void find(String id);

    /**
     * 发起信息发送消息
     *
     * @param id subscription id
     */
    void send(String id);

    /**
     * 分页查询信息定义（附带用户关注信息）
     *
     * @param page    页码
     * @param size    页大小
     * @param keyword 搜索关键字
     * @param userId  用户ID
     * @return 分页数据
     */
    PageInfo<InfoDTO> list(Integer page, Integer size, String keyword, String userId);
}
