package xyz.ipush.web.exception;

/**
 * 系统业务异常
 *
 * @author jwei
 * @date 2020/12/17
 */
public class IPushException extends Exception {

    private final ErrorCodeEnum errorCodeEnum;

    public static IPushException build(ErrorCodeEnum error) {
        return new IPushException(error);
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    private IPushException(ErrorCodeEnum error) {
        this.errorCodeEnum = error;
    }

    @Override
    public String getMessage() {
        return errorCodeEnum.getMsg();
    }
}
