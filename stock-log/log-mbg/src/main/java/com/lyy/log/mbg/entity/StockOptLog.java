package com.lyy.log.mbg.entity;

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
 * 系统操作日志表
 * </p>
 *
 * @author lyy
 * @since 2023-03-27
 */
@Getter
@Setter
@TableName("stock_opt_log")
public class StockOptLog implements Serializable {

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
