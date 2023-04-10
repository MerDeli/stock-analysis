package com.lyy.stock.ums.server.controller;

import cn.hutool.core.collection.CollUtil;
import com.lyy.stock.common.core.api.ResponseData;
import com.lyy.stock.common.core.exception.CommonExceptionCode;
import com.lyy.stock.ums.mbg.entity.form.StockUserLoginForm;
import com.lyy.stock.ums.mbg.entity.form.StockUserRegisterForm;
import com.lyy.stock.ums.mbg.entity.po.StockUser;
import com.lyy.stock.ums.mbg.entity.vo.StockUserRegisterVo;
import com.lyy.stock.ums.mbg.exception.UmsExceptionCode;
import com.lyy.stock.ums.mbg.service.StockUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lyy
 * @since 2023-03-21
 */
@RestController
@Api(tags = "StockUserController", description = "后台用户管理")
@RequestMapping("/stockUser")
public class StockUserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private StockUserService stockUserService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData<StockUserRegisterVo> register(@RequestBody StockUserRegisterForm stockUserRegisterForm) {
        StockUserRegisterVo registerVo = stockUserService.register(stockUserRegisterForm);
        if (registerVo == null) {
            ResponseData.error(UmsExceptionCode.USER_NOT_EXIST);
        }
        return ResponseData.success(registerVo);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(@RequestBody StockUserLoginForm stockUserLoginForm) {
        String token = stockUserService.login(stockUserLoginForm.getUsername(), stockUserLoginForm.getPassword());
        if (token == null) {
            return ResponseData.error(UmsExceptionCode.USERNAME_OR_PASSWORD_ERROR);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return ResponseData.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = stockUserService.refreshToken(token);
        if (refreshToken == null) {
            return ResponseData.error(CommonExceptionCode.JWT_TOKEN_EXPIRED);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return ResponseData.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData getAdminInfo(Principal principal) {
        if (principal == null) {
            return ResponseData.error(CommonExceptionCode.JWT_ILLEGAL_ARGUMENT);
        }
        String username = principal.getName();
        StockUser stockUser = stockUserService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", stockUser.getUsername());
        data.put("menus", roleService.getMenuList(stockUser.getId()));
        data.put("icon", stockUser.getIcon());
        List<UmsRole> roleList = stockUserService.getRoleList(stockUser.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return ResponseData.success(data);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData logout() {
        return ResponseData.success(null);
    }
}
