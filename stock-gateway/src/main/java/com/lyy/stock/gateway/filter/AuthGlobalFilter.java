package com.lyy.stock.gateway.filter;

import cn.hutool.core.util.StrUtil;
import com.lyy.stock.common.core.context.HeaderCode;
import com.lyy.stock.common.core.exception.StockException;
import com.lyy.stock.common.token.model.JwtUserInfo;
import com.lyy.stock.gateway.constant.OrderConstant;
import com.lyy.stock.gateway.enums.GatewayExceptionCode;
import com.lyy.stock.gateway.utils.ClientIp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author:
 * @createTime: 2023/03/28 09:56:48
 * @version:
 * @Description:
 */
@Slf4j
@Component
public class AuthGlobalFilter extends BaseFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 租户
        String clientCode = exchange.getRequest().getHeaders().getFirst(HeaderCode.CLIENT_CODE);
        // 租户
        String tenantCode = exchange.getRequest().getHeaders().getFirst(HeaderCode.TENANT_CODE);
        // token
        String token = exchange.getRequest().getHeaders().getFirst(HeaderCode.TOKEN);
        // identityType
        String identityType = exchange.getRequest().getHeaders().getFirst(HeaderCode.IDENTITY_TYPE);

        log.info("请求路径:{}", exchange.getRequest().getPath());
        log.info("请求租户:{}", tenantCode);
        log.info("请求客户端:{}", clientCode);
        log.info("请求token:{}", token);
        log.info("请求身份类型:{}", identityType);

        //设置请求头
        Map<String, String> headers = new HashMap<>();

        if (!super.checkPath(super.getPath(exchange), gatewayProperties.getNoIdentityPath())) {
            if (StrUtil.isBlank(token)) {
                throw new StockException(GatewayExceptionCode.TOKEN_EMPTY);
            }
            JwtUserInfo authInfo = authUtil.getAuthInfo(token);
            Optional.ofNullable(authInfo).orElseThrow(() -> new StockException(GatewayExceptionCode.TOKEN_INVALID));
            headers.put(HeaderCode.ACCOUNT_ID, String.valueOf(authInfo.getAccountId()));
            headers.put(HeaderCode.IDENTITY_ID, String.valueOf(authInfo.getIdentityId()));
            headers.put(HeaderCode.DEPT_ID, String.valueOf(authInfo.getDeptId()));
            headers.put(HeaderCode.DEPT_CODE, authInfo.getDeptCode());
            headers.put(HeaderCode.USERNAME, authInfo.getUsername());
            headers.put(HeaderCode.DATA_SCOPE, String.valueOf(authInfo.getDeptCode()));
        }

        headers.put(HeaderCode.IDENTITY_TYPE, identityType);
        headers.put(HeaderCode.TENANT_CODE, tenantCode);
        headers.put(HeaderCode.CLIENT_CODE, clientCode);
        headers.put(HeaderCode.IP, ClientIp.getIp(exchange.getRequest()));
        setHeader(exchange, headers);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return OrderConstant.IDENTITY_ORDER;
    }
}
