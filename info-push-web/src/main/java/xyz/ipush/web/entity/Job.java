package xyz.ipush.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 任务执行记录
 * </p>
 *
 * @author jwei
 * @date 2021/03/07
 */
@Data
@TableName("job")
public class Job implements Serializable {

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
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 
     */
    @TableField(value = "job_name")
    private String jobName;

    /**
     * 0/1/2 发起/成功/失败
     */
    @TableField(value = "job_status")
    private Integer jobStatus;

    /**
     * 
     */
    @TableField(value = "description")
    private String description;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

}
