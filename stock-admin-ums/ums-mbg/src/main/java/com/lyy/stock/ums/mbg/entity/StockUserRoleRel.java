package com.lyy.stock.ums.mbg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
@Getter
@Setter
@TableName("stock_user_role_rel")
public class StockUserRoleRel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("role_id")
    private Long roleId;


}
