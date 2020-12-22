package fun.jwei.ipush.web.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 用户信息
 *
 * @author jwei
 * @date 2020/12/22
 */
@Data
public class UserDTO {
    /**
     * 用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 头像url
     */
    @TableField(value = "avatar")
    private String avatar;
}
