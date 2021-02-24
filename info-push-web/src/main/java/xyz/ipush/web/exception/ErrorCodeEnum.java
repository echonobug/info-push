package xyz.ipush.web.exception;

/**
 * 系统错误码
 *
 * @author jwei
 * @date 2020/12/21
 */
public enum ErrorCodeEnum {
    /**
     * 登录相关异常
     */
    USERNAME_NOT_FOUND(10001, "账号不存在！"),
    PASSWORD_NOT_MATCHED(10002, "用户名或密码错误！"),
    NOT_LOGIN(10003, "您当前未登录，请先登录！"),
    INVALID_TOKEN(10004, "登录已失效，请重新登录！"),
    ACCOUNT_NOT_ENABLE(10005,"账号不可用，请先到邮箱激活您的账号！"),

    /**
     * 注册相关异常
     */
    EMAIL_ALREADY_EXISTS(10201, "该邮箱已注册，请直接登录！"),
    INVALID_ACTIVE_URL(10202,"激活失败！链接非法！"),
    USERNAME_ALREADY_EXISTS(10203,"该用户名已被注册！"),

    /**
     * 权限相关
     */
    NOT_AUTHORIZED(10101, "没有权限！"),

    /**
     * 未知错误
     */
    UNKNOWN_ERROR(20000, "未知错误！");

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误信息
     */
    private final String msg;


    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
