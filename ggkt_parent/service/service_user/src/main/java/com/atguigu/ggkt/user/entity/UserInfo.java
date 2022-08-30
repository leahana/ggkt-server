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
 * 用户表
 * </p>
 *
 * @author LeahAna
 * @since 2022-08-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 头像
     */
    private String avatar;

    private String province;

    /**
     * 0：未订阅 1：已订阅
     */
    private Integer subscribe;

    /**
     * 小程序open id
     */
    private String openId;

    /**
     * 微信开放平台unionID
     */
    private String unionId;

    /**
     * 推荐人用户id
     */
    private Long recommendId;

    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isDeleted;


}
