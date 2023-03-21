package com.lyy.stock.common.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author:
 * @createTime: 2023/03/21 15:52:20
 * @version:
 * @Description:
 */
@Data
public class BaseEntity {
    /**
     * 主键id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createName;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    /**
     * 更新人
     */
    @TableField(fill = FieldFill.UPDATE)
    private Long updateName;


    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    /**
     * 删除状态(0-正常,1-已删除)
     */
    @TableLogic
    private Integer deleteFlag;

}
