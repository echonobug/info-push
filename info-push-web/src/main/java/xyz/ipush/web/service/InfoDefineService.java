package xyz.ipush.web.service;

import xyz.ipush.web.entity.InfoDefine;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jwei
 * @since 2021/03/02
 */
public interface InfoDefineService extends IService<InfoDefine> {

    void testFind(String id);
    void testSend(String id);
}
