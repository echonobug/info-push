package xyz.ipush.web.vo;

import lombok.Data;
import lombok.NonNull;

/**
 * 用户注册信息
 *
 * @author jwei
 * @date 2021/1/20
 */
@Data
public class UserRegisterVO {
    @NonNull
    String email;
    @NonNull
    String password;
}
