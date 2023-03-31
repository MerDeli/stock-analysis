package com.lyy.stock.auth.component;

import cn.hutool.json.JSONUtil;
import com.lyy.stock.common.core.api.ResponseData;
import com.lyy.stock.common.core.exception.CommonExceptionCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:
 * @createTime: 2023/03/31 15:48:33
 * @version:
 * @Description: 自定义返回结果：未登录或登录过期
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ResponseData.error(CommonExceptionCode.LOGIN_ERROR.getCode(),authException.getMessage())));
        response.getWriter().flush();
    }
}
