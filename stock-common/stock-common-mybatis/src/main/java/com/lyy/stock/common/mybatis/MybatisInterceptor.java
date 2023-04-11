package com.lyy.stock.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lyy.stock.common.core.context.SpringContextUtils;
import com.lyy.stock.common.core.enumerate.DeleteFlagEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author hfbin
 * @email huangfubin00@gmail.com
 * @date 2019-09-29 22:37
 * @description: 自动填充创建人 时间 https://mp.baomidou.com/guide/auto-fill-metainfo.html
 */
@Slf4j
@Component
public class MybatisInterceptor implements MetaObjectHandler {

    /**
     * 如果请求的实体有参数则默认不会替换值
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        if (metaObject.hasGetter("createdTime")) {
            this.strictInsertFill(metaObject, "createdTime", LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasGetter("createdName")) {
            this.strictInsertFill(metaObject, "createdName", String.class, SpringContextUtils.getUsername());
        }
        if (metaObject.hasGetter("updatedTime")) {
            this.strictInsertFill(metaObject, "updatedTime", LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasGetter("updatedName")) {
            this.strictInsertFill(metaObject, "updatedName", String.class, SpringContextUtils.getUsername());
        }
        if (metaObject.hasGetter("deleteFlag")) {
            this.strictInsertFill(metaObject, "deleteFlag", Integer.class, DeleteFlagEnum.NOT_DELETE.getCode());
        }
    }

    /**
     * 如果请求的实体有参数则默认不会替换值
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        if (metaObject.hasGetter("updatedTime")) {
            this.strictUpdateFill(metaObject, "updatedTime", LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasGetter("updatedName")) {
            this.strictUpdateFill(metaObject, "updatedName", String.class, SpringContextUtils.getUsername());
        }
    }
}
