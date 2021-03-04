package xyz.ipush.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>
 * 根据信息定义获取的数据
 * </p>
 *
 * @author jwei
 * @date 2021/03/02
 */
@Data
@TableName("info_data")
public class InfoData implements Serializable {

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
    @TableField(value = "content")
    private String content;

    /**
     * 
     */
    @TableField(value = "create_time")
    private Date createTime;

}
