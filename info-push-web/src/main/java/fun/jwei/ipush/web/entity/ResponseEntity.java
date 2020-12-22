package fun.jwei.ipush.web.entity;

import fun.jwei.ipush.web.exception.ErrorCodeEnum;
import fun.jwei.ipush.web.exception.IPushException;
import fun.jwei.ipush.web.exception.ResponseCodeEnum;
import lombok.Data;

/**
 * 请求响应数据
 *
 * @author jwei
 * @date 2020/12/17
 */
@Data
public class ResponseEntity {
    private Integer code;
    private String msg;
    private Object data;

    /**
     * 请求成功
     *
     * @return 响应体
     */
    public static ResponseEntity success() {
        return success(null);
    }

    /**
     * 请求成功
     *
     * @param data 返回数据
     * @return 响应体
     */
    public static ResponseEntity success(Object data) {
        return new ResponseEntity(200, "请求成功！", data);
    }

    /**
     * 请求出错
     *
     * @param error 错误枚举
     * @return 响应体
     */
    public static ResponseEntity error(ErrorCodeEnum error) {
        return new ResponseEntity(error);
    }

    /**
     * 请求出错
     *
     * @param error 系统异常
     * @return 响应体
     */
    public static ResponseEntity error(IPushException error) {
        return error(error.getErrorCodeEnum());
    }


    private ResponseEntity(ErrorCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    private ResponseEntity(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
