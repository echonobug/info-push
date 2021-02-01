package xyz.ipush.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.ipush.web.dto.UserDTO;
import xyz.ipush.web.entity.User;
import xyz.ipush.web.exception.ErrorCodeEnum;
import xyz.ipush.web.exception.IPushException;
import xyz.ipush.web.mapper.UserMapper;
import xyz.ipush.web.service.UserService;
import xyz.ipush.web.util.SecurityContextUtil;
import xyz.ipush.web.util.TokenUtil;
import xyz.ipush.web.vo.UserLoginVO;
import xyz.ipush.web.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author jwei
 * @date 2020/12/16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = query().eq("name", s).or().eq("email", s).one();
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        return user;
    }

    @Override
    public String login(UserLoginVO userVO) {
        UserDetails userDetails = loadUserByUsername(userVO.getName());
        if (!passwordEncoder.matches(userVO.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误！");
        }
        return tokenUtil.generateToken(userDetails, userVO.getRememberMe());
    }

    @Override
    public UserDTO getInfo() {
        User user = SecurityContextUtil.getUser();
        assert user != null;
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAvatar(user.getAvatar());
        return userDTO;
    }

    @Override
    public void register(UserRegisterVO userVO) throws IPushException {
        List<User> users = query().eq("email", userVO.getEmail()).list();
        if (!users.isEmpty()) {
            throw IPushException.build(ErrorCodeEnum.EMAIL_ALREADY_EXISTS);
        }
        User user = new User();
        user.setEmail(userVO.getEmail());
        user.setPassword(passwordEncoder.encode(userVO.getPassword()));
        save(user);
    }


}
