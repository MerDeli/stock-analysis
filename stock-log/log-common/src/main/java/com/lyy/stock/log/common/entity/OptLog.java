package com.lyy.stock.log.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:
 * @createTime: 2023/03/27 11:11:35
 * @version:
 * @Description:
 */
@Data
public class OptLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("opt_type")
    private Boolean optType;

    @TableField("opt_behavior")
    private Boolean optBehavior;

    @TableField("ip")
    private String ip;

    @TableField("location")
    private String location;

    @TableField("client_code")
    private String clientCode;

    @TableField("req_url")
    private String reqUrl;

    @TableField("user_agent")
    private String userAgent;

    @TableField("http_method")
    private String httpMethod;

    @TableField("req_param")
    private String reqParam;

    @TableField("res_info")
    private String resInfo;

    @TableField("res_status")
    private Boolean resStatus;

    @TableField("description")
    private String description;

    @TableField("tenant_code")
    private String tenantCode;

    @TableField("created_name")
    private Long createdName;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField("updated_time")
    private Date updatedTime;

    @TableField("updated_name")
    private String updatedName;

    @TableField("delete_flag")
    @TableLogic
    private Integer deleteFlag;

}
