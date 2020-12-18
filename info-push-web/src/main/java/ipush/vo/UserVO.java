package ipush.vo;

import lombok.Data;
import lombok.NonNull;

/**
 * 用户
 *
 * @author jwei
 * @date 2020/12/17
 */
@Data
public class UserVO {
    @NonNull
    String name;
    @NonNull
    String password;
}
