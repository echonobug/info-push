package xyz.ipush.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.ipush.web.dto.UserDTO;
import xyz.ipush.web.entity.User;
import xyz.ipush.web.exception.IPushException;
import xyz.ipush.web.vo.UserLoginVO;
import xyz.ipush.web.vo.UserRegisterVO;

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
    String login(UserLoginVO userVO);

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    UserDTO getInfo();

    /**
     * 用户注册
     *
     * @param userVO 用户信息
     * @throws IPushException exception
     */
    void register(UserRegisterVO userVO) throws IPushException;
}
