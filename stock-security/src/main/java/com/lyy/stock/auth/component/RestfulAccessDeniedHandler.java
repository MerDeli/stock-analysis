package com.lyy.stock.auth.component;

import cn.hutool.json.JSONUtil;
import com.lyy.stock.common.core.api.ResponseData;
import com.lyy.stock.common.core.exception.CommonExceptionCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:
 * @createTime: 2023/03/31 15:49:36
 * @version:
 * @Description: 自定义返回结果：没有权限访问时
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(ResponseData.error(CommonExceptionCode.SYSTEM_UNAUTHORIZED.getCode(),e.getMessage())));
        response.getWriter().flush();
    }
}