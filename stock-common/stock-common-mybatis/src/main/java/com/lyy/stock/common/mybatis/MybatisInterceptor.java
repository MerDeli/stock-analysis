package com.lyy.stock.common.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lyy.stock.common.core.context.SpringContextUtils;
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
        if (metaObject.hasGetter("createTime")) {
            this.strictInsertFill(metaObject, "createTime", LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasGetter("createBy")) {
            this.strictInsertFill(metaObject, "createBy", Long.class, SpringContextUtils.getIdentityId());
        }
        if (metaObject.hasGetter("updateTime")) {
            this.strictInsertFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasGetter("updateBy")) {
            this.strictInsertFill(metaObject, "updateBy", Long.class, SpringContextUtils.getIdentityId());
        }
        if (metaObject.hasGetter("delFlag")) {
            this.strictInsertFill(metaObject, "delFlag", Integer.class, 0);
        }
        if (metaObject.hasGetter("tenantCode")) {
            this.strictInsertFill(metaObject, "tenantCode", String.class, SpringContextUtils.getTenantCode());
        }
    }

    /**
     * 如果请求的实体有参数则默认不会替换值
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        if (metaObject.hasGetter("updateTime")) {
            this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        }
        if (metaObject.hasGetter("updateBy")) {
            this.strictUpdateFill(metaObject, "updateBy", Long.class, SpringContextUtils.getIdentityId());
        }
    }
}
