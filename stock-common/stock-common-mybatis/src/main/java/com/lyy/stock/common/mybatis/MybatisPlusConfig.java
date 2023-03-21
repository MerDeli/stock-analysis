package com.lyy.stock.common.mybatis;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.extension.injector.methods.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.InsertBatchSomeColumn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Author:
 * @createTime: 2023/03/21 14:57:04
 * @version:
 * @Description:
 */
@Configuration
public class MybatisPlusConfig {




    /**
     * sql增强
     * @return
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector() {
            @Override
            public List<AbstractMethod> getMethodList(Class<?> mapperClass,TableInfo tableInfo) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass,tableInfo);
                methodList.add(new InsertBatchSomeColumn());
                methodList.add(new AlwaysUpdateSomeColumnById());
                return methodList;
            }
        };
    }

}
