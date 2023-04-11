package com.lyy.stock.ums.mbg.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
@Getter
@Setter
@TableName("stock_user")
public class StockUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("nickname")
    private String nickname;

    @TableField("name")
    private String name;

    @TableField("icon")
    private String icon;

    @TableField("mobile")
    private String mobile;

    @TableField("email")
    private String email;

    @TableField("remark")
    private String remark;

    @TableField("user_status")
    private Integer userStatus;

    @TableField(value = "recently_login_time", fill = FieldFill.INSERT)
    private Date recentlyLoginTime;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    private Date updatedTime;

    @TableField(value = "created_name", fill = FieldFill.INSERT)
    private String createdName;

    @TableField(value = "updated_name", fill = FieldFill.INSERT_UPDATE)
    private String updatedName;

    @TableField(value = "delete_flag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer deleteFlag;


}
