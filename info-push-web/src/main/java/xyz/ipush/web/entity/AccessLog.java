package xyz.ipush.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 访问日志
 * </p>
 *
 * @author jwei
 * @date 2020/12/18
 */
@Data
@TableName("access_log")
public class AccessLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户ip
     */
    @TableField(value = "request_ip")
    private String requestIp;

    /**
     * http请求方法
     */
    @TableField(value = "request_method")
    private String requestMethod;

    /**
     * 用户请求url
     */
    @TableField(value = "request_url")
    private String requestUrl;

    /**
     * 处理请求耗时
     */
    @TableField(value = "request_duration")
    private Long requestDuration;

    /**
     * 响应状态码
     */
    @TableField(value = "response_status")
    private Integer responseStatus;

    /**
     * 记录创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

}
