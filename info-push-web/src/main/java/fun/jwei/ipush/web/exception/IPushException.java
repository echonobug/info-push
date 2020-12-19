package fun.jwei.ipush.web.exception;

/**
 * 系统业务异常
 *
 * @author jwei
 * @date 2020/12/17
 */
public class IPushException extends Exception {
    public static IPushException build(String msg) {
        return new IPushException(msg);
    }

    public IPushException(String message) {
        super(message);
    }
}
