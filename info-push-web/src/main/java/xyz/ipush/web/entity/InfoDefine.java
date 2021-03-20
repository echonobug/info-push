package xyz.ipush.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * <p>
 * 信息定义
 * </p>
 *
 * @author jwei
 * @date 2021/03/16
 */
@Data
@TableName("info_define")
public class InfoDefine implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableField(value = "id")
    private String id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     *
     */
    @TableField(value = "description")
    private String description;

    /**
     * 图片url
     */
    @TableField(value = "image")
    private String image;

    /**
     *
     */
    @TableField(value = "content")
    private String content;

    /**
     *
     */
    @TableField(value = "update_cron")
    private String updateCron;

    /**
     *
     */
    @TableField(value = "creator_id")
    private String creatorId;

    /**
     *
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private Date createTime;

}
