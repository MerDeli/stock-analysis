package com.lyy.stock.ums.mbg.entity.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @Author:
 * @createTime: 2023/04/18 14:42:29
 * @version:
 * @Description:
 */
@Data
@Document("emp")
public class Emp implements Serializable {

    @Id
    private Long id;
    @Field("username")
    private String name;
    @Field
    private int age;
}
