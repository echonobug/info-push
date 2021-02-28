package xyz.ipush.web.vo;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("邮箱")
    @NonNull
    String email;
    @ApiModelProperty("用户名")
    @NonNull
    String username;
    @ApiModelProperty("密码")
    @NonNull
    String password;
}
