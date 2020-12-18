package ipush.exception;

/**
 * 响应码枚举类
 *
 * @author jwei
 */
public enum ResponseCodeEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功！");


    /**
     * 响应状态码
     */
    private final Integer code;

    /**
     * 响应信息
     */
    private final String msg;


    ResponseCodeEnum(Integer code, String msg) {
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
