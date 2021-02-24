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
    String login(UserLoginVO userVO) throws IPushException;

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

    /**
     * 账号激活
     *
     * @param content 账号信息
     */
    void active(String content) throws IPushException;

    /**
     * 检测用户名是否可用
     *
     * @param username 用户名
     */
    void validateUsername(String username) throws IPushException;
}
