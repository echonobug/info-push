package fun.jwei.ipush.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import fun.jwei.ipush.web.dto.UserDTO;
import fun.jwei.ipush.web.entity.User;
import fun.jwei.ipush.web.exception.ErrorCodeEnum;
import fun.jwei.ipush.web.exception.IPushException;
import fun.jwei.ipush.web.mapper.UserMapper;
import fun.jwei.ipush.web.service.UserService;
import fun.jwei.ipush.web.util.TokenUtil;
import fun.jwei.ipush.web.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public String login(UserVO userVO) {
        UserDetails userDetails = loadUserByUsername(userVO.getName());
        if (!passwordEncoder.matches(userVO.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码错误！");
        }
        return tokenUtil.generateToken(userDetails);
    }

    @Override
    public UserDTO getInfo(String token) throws IPushException {
        if (!StringUtils.hasLength(token)) {
            throw IPushException.build(ErrorCodeEnum.NOT_LOGIN);
        }
        String username = tokenUtil.getUsernameFromToken(token);
        UserDetails userDetails = loadUserByUsername(username);
        if (!tokenUtil.validateToken(token, userDetails)) {
            throw IPushException.build(ErrorCodeEnum.INVALID_TOKEN);
        }
        User user = (User) userDetails;
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setAvatar(user.getAvatar());
        return userDTO;
    }


}
