package fun.jwei.ipush.web.service;

import fun.jwei.ipush.web.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.jwei.ipush.web.vo.UserVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jwei
 * @date 2020/12/16
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录
     *
     * @param userVO 用户信息
     * @return token
     */
    String login(UserVO userVO);
}
