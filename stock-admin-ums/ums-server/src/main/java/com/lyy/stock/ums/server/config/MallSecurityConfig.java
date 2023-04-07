package com.lyy.stock.ums.server.config;

import com.lyy.stock.auth.component.DynamicSecurityService;
import com.lyy.stock.auth.config.SecurityConfig;
import com.lyy.stock.ums.mbg.entity.po.StockResource;
import com.lyy.stock.ums.mbg.service.StockResourceService;
import com.lyy.stock.ums.mbg.service.StockUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:
 * @createTime: 2023/03/31 16:00:38
 * @version:
 * @Description:
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallSecurityConfig extends SecurityConfig {

    @Autowired
    private StockUserService stockUserService;
    @Autowired
    private StockResourceService stockResourceService;


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> stockUserService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
            List<StockResource> resourceList = stockResourceService.listAll();
            for (StockResource resource : resourceList) {
                map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
            }
            return map;
        };
    }
}
