package xyz.ipush.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 订阅表
 * </p>
 *
 * @author jwei
 * @date 2021/03/06
 */
@Data
@TableName("subscription")
public class Subscription implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableField(value = "id")
    private String id;

    /**
     * 
     */
    @TableField(value = "info_define_id")
    private String infoDefineId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 
     */
    @TableField(value = "cron")
    private String cron;

    /**
     * 
     */
    @TableField(value = "created_time")
    private Date createdTime;

}
