package ipush.entity;

import ipush.exception.ResponseCodeEnum;
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
        return new ResponseEntity(ResponseCodeEnum.SUCCESS);
    }

    /**
     * 请求成功
     *
     * @param data 返回数据
     * @return 响应体
     */
    public static ResponseEntity success(Object data) {
        return new ResponseEntity(ResponseCodeEnum.SUCCESS, data);
    }

    /**
     * 请求出错
     *
     * @param error 错误枚举
     * @return 响应体
     */
    public static ResponseEntity error(ResponseCodeEnum error) {
        return new ResponseEntity(error);
    }

    private ResponseEntity(ResponseCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    private ResponseEntity(ResponseCodeEnum codeEnum, Object data) {
        this(codeEnum);
        this.data = data;
    }
}
