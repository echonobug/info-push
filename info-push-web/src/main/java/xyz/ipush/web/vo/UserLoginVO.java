package xyz.ipush.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

/**
 * 用户
 *
 * @author jwei
 * @date 2020/12/17
 */
@Data
public class UserLoginVO {
    @ApiModelProperty("用户名")
    @NonNull
    String name;
    @ApiModelProperty("密码")
    @NonNull
    String password;
    @ApiModelProperty("是否记住密码")
    @NonNull
    Boolean rememberMe;
}
