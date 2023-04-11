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
 * 后台资源表
 * </p>
 *
 * @author lyy
 * @since 2023-04-07
 */
@Getter
@Setter
@TableName("stock_resource")
public class StockResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("url")
    private String url;

    @TableField("description")
    private String description;

    @TableField("category_id")
    private Long categoryId;

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
