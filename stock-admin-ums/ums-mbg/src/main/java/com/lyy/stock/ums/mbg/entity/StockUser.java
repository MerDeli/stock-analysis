package com.lyy.stock.ums.mbg.entity;

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

    @TableField("nickename")
    private String nickename;

    @TableField("name")
    private String name;

    @TableField("mobile")
    private String mobile;

    @TableField("email")
    private String email;

    @TableField("user_status")
    private Integer userStatus;

    @TableField("recently_login_time")
    private Date recentlyLoginTime;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField("created_name")
    private String createdName;

    @TableField("updated_name")
    private String updatedName;

    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;


}
