package xyz.ipush.web.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author jwei
 * @date 2020/12/16
 */
@Data
@TableName("user")
public class User implements UserDetails, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 32位UUID
     */
    @TableField(value = "id")
    private String id;

    /**
     * 用户名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 邮箱地址
     */
    @TableField(value = "email")
    private String email;

    /**
     * 头像url
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 账号是否可用
     */
    @TableField(value = "enable")
    private Integer enable;

    /**
     * 账号是否被锁定
     */
    @TableField(value = "locked")
    private Integer locked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_user"));
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.locked == 0;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enable == 1;
    }
}
