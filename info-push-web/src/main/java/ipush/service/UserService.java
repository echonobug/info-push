package ipush.service;

import ipush.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import ipush.vo.UserVO;

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
