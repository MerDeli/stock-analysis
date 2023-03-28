package com.lyy.stock.common.token.config;

import com.lyy.stock.common.token.utils.AuthUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:
 * @createTime: 2023/03/28 09:35:13
 * @version:
 * @Description:
 */
@Configuration
public class TokenAutoConfig {

    @Bean
    public AuthUtil authUtil(){
        return new AuthUtil();
    }
}
