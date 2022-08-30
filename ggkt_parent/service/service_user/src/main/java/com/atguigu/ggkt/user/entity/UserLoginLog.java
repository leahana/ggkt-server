package com.atguigu.ggkt.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登陆记录表
 * </p>
 *
 * @author LeahAna
 * @since 2022-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserLoginLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 登录ip
     */
    private String ip;

    /**
     * 登录城市
     */
    private String city;

    /**
     * 登录类型【0-web，1-移动】
     */
    private Boolean type;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标记（0:不可用 1:可用）
     */
    private Integer isDeleted;


}
