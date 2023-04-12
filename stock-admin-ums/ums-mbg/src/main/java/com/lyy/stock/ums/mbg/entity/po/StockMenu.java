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
 * 
 * </p>
 *
 * @author lyy
 * @since 2023-04-12
 */
@Getter
@Setter
@TableName("stock_menu")
public class StockMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("parent_id")
    private Long parentId;

    @TableField("title")
    private String title;

    @TableField("level")
    private Integer level;

    @TableField("sort")
    private Integer sort;

    @TableField("name")
    private String name;

    @TableField("icon")
    private String icon;

    @TableField("hidden")
    private Boolean hidden;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField(value = "created_name", fill = FieldFill.INSERT)
    private String createdName;

    @TableField("updated_name")
    private String updatedName;

    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;


}
