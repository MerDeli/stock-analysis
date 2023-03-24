package com.lyy.stock.common.log.annotation;

import com.lyy.stock.common.log.enums.LogTypeEnum;
import com.lyy.stock.common.log.enums.OptBehaviorEnum;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Log {
    /**
     * 描述
     *
     * @return {String}
     */
    String desc() default "";

    /**
     * 日志类型
     *
     * @return {int}
     */
    LogTypeEnum logType() default LogTypeEnum.OPT_LOG;

    /**
     * 操作行为
     *
     * @return {int}
     */
    OptBehaviorEnum optBehavior() default OptBehaviorEnum.NOT;

    /**
     * 记录执行参数
     *
     * @return
     */
    boolean request() default true;

    /**
     * 记录返回参数
     *
     * @return
     */
    boolean response() default true;
}
