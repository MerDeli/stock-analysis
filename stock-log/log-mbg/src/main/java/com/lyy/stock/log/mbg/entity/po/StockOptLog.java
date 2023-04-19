package com.lyy.stock.log.mbg.entity.po;

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
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Document("stock_opt_log")
public class StockOptLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @Id
    private Long id;

    @TableField("username")
    @Field
    private String username;

    @TableField("opt_type")
    @Field
    private Integer optType;

    @TableField("opt_behavior")
    @Field
    private Integer optBehavior;

    @TableField("ip")
    @Field
    private String ip;

    @TableField("location")
    @Field
    private String location;

    @TableField("client_code")
    @Field
    private String clientCode;

    @TableField("req_url")
    @Field
    private String reqUrl;

    @TableField("user_agent")
    @Field
    private String userAgent;

    @TableField("http_method")
    @Field
    private String httpMethod;

    @TableField("req_param")
    @Field
    private String reqParam;

    @TableField("res_info")
    @Field
    private String resInfo;

    @TableField("res_status")
    @Field
    private Integer resStatus;

    @TableField("description")
    @Field
    private String description;

    @TableField("tenant_code")
    @Field
    private String tenantCode;

    @TableField(value = "created_name",fill = FieldFill.INSERT)
    @Field
    private String createdName;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    @Field
    private Date createdTime;

    @TableField(value = "updated_time", fill = FieldFill.INSERT_UPDATE)
    @Field
    private Date updatedTime;

    @TableField(value = "updated_name", fill = FieldFill.INSERT_UPDATE)
    @Field
    private String updatedName;

    @TableField(value = "delete_flag",fill = FieldFill.INSERT)
    @TableLogic
    @Field
    private Integer deleteFlag;


}
